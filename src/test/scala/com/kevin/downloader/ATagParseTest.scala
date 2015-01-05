package com.kevin.downloader

import org.scalatest._
import com.kevin.downloader.DownloadActor._
import java.net.URL

class ATagParseTest extends FlatSpec {

	"a test " should "succeed" in {
		val url: URL = new URL("http://www.cis.upenn.edu/~matuszek/index.html")
		val result = getValidATags(url)
		println("printing results...")
		println( result.flatMap(x => List("FOOO" + x)) )
	}
}