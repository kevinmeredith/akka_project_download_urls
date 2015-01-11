package com.kevin.downloader

import org.apache.commons.io.IOUtils
import java.net.URL

object Common {
	case class DownloadUrl(relativePath: String)	

    private[downloader] def getUrlContent(url: URL): String = {
		println("reading url: " + url)
		val inputStream = url.openStream
		IOUtils.toString(inputStream)
	}
}