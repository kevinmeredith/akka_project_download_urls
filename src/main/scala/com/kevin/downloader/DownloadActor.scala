package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import akka.routing.FromConfig
import com.kevin.downloader.Worker
import scala.util.matching.Regex
import java.net.URL
import java.io.InputStream
import org.apache.commons.io.IOUtils

object DownloadActor {

    private def getUrlContent(url: URL): String = {
		println("reading url: " + url)
		val inputStream = url.openStream
		IOUtils.toString(inputStream)
	}

	// TODO: download PNGs as well

	def getValidATags(url: URL): List[(String, Int)] = {
		val urlContent           				   = getUrlContent(url)
		val aTags: List[String] 				   = urlContent.split("<a href=\"").toList
		val matchingATags: List[String] 		   = aTags.map(x => x.takeWhile(y => y != '"'))
		val removedDocTypeAndHashes: List[String]  = matchingATags.filter{ 
			                                                x => !x.contains("DOCTYPE") && !x.toLowerCase.startsWith("http") && !x.contains("#")
													 }
	    removedDocTypeAndHashes.zipWithIndex
	}

}

class DownloadActor extends Actor with ActorLogging {

	import DownloadActor._

	val router = context.actorOf( Props[Worker].withRouter( FromConfig()), "Router")

	def receive  = {
		case url: URL => {
			val htmlsInPage: List[(String, Int)] = getValidATags(url)
			log.info(s"found ${htmlsInPage.length} for the url: $url")
			htmlsInPage.foreach(router ! _)
		}
		case x        => log.error(s"error. Couldn't match $x to `url`")
	}
	
}