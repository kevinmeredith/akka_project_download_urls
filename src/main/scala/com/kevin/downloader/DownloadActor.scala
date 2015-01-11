package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import akka.routing.FromConfig
import scala.util.matching.Regex
import java.net.URL
import java.io.{File, InputStream}

object DownloadActor {

	import Common._

	// TODO: download PNGs as well

	def getValidATags(url: URL): List[String] = {
		val urlContent           				   = getUrlContent(url)
		val aTags: List[String] 				   = urlContent.split("<a href=\"").toList
		val matchingATags: List[String] 		   = aTags.map(x => x.takeWhile(y => y != '"'))
		val removedDocTypeAndHashes: List[String]  = matchingATags.filter{ 
			                                                x => !x.contains("DOCTYPE") && !x.toLowerCase.startsWith("http") && !x.contains("#")
													 }
	    removedDocTypeAndHashes
	}

}

class DownloadActor extends Actor with ActorLogging {

	import DownloadActor._
	import Common._

	val router = context.actorOf( Props[Worker].withRouter( FromConfig()), "Router")

	def receive  = {
		case url: URL => {
			val htmlsInPage: List[String] = getValidATags(url)
			val parentPath: String        = new File( url.toString ).toPath.getParent.toString + "/"
			log.info(s"found ${htmlsInPage.length} for the url: $url")
			htmlsInPage.foreach(router ! DownloadUrl(parentPath.replaceAll("/", "//"), _) )
		}
		case fail     => log.error(s"error. Couldn't match $fail to `url`")
	}
	
}