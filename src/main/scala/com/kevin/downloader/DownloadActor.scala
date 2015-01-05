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
		val urlContent           				= getUrlContent(url)
		val aTags: List[String] 			    = urlContent.split("<a href=\"").toList
		val matchingATags: List[Option[String]] = aTags.map(x => HTML_PATTERN_REGEX.findFirstIn(x))
		val flattened: List[String]             = matchingATags.flatten 
		//flattened.map(x => x.takeWhile(y => y != '"')) // keep until reaching the ending quote of the URL
		// TODO: use above (line 27) appproach, but simply split on "a href = ...", and then do `takeWhile`
		flattened
	}

}

class DownloadActor extends Actor {

	import DownloadActor._

	def receive  = {
		case url: URL => println( getValidATags( url ) )
		case x        => println(s"error. Couldn't match $x to `url`")
	}
	
}