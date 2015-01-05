package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem}
import java.net.URL
import java.io.InputStream
import org.apache.commons.io.IOUtils

object DownloadActor {
	val HTML_PATTERN_REGEX = """(?i).*<\s*(a)\s+.*href\s*=\s*['"]?([^'" ]+\.html?)['" >].*""".r

    private def getUrlAsString(url: URL): String = {
		println("reading url: " + url)
		val inputStream = url.openStream
		IOUtils.toString(inputStream)
	}

}

class DownloadActor extends Actor {

	import DownloadActor._

	def receive  = {
		case url: URL => println( getUrlAsString(url) )
		case x        => println(s"error. Couldn't match $x to `url`")
	}
	
}