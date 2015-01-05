package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem}
import scala.util.matching.Regex
import java.net.URL
import java.io.InputStream
import org.apache.commons.io.IOUtils

object DownloadActor {
	val HTML_PATTERN_REGEX: Regex = """(?i).*<\s*(a)\s+.*href\s*=\s*['"]?([^'" ]+\.html?)['" >].*""".r

    private def getUrlContent(url: URL): String = {
		println("reading url: " + url)
		val inputStream = url.openStream
		IOUtils.toString(inputStream)
	}

	private def getHtmlAndPngs(url: URL): (Set[URL], Set[URL]) = {
		(Set(), Set())    
	}

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

class DownloadActor extends Actor {

	import DownloadActor._

	def receive  = {
		case url: URL => println( getValidATags( url ) )
		case x        => println(s"error. Couldn't match $x to `url`")
	}
	
}