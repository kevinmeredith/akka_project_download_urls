package com.kevin.downloader

import org.apache.commons.io.IOUtils
import java.net.URL

object Common {

	// TODO: make dynamic
	val DOWNLOAD_URL 		= "http://www.cis.upenn.edu/~matuszek/index.html"

	case class DownloadUrl(parentPath: String, relativePath: String)	

    private[downloader] def getUrlContent(url: URL): String = {
		println("reading url: " + url)
		val inputStream = url.openStream
		IOUtils.toString(inputStream)
	}
}