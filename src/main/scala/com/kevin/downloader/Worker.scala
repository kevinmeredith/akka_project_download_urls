package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import java.io.{File, PrintWriter}
import java.net.URL

object Worker {
	val downloadPath: String = "output/download_dir/"

	import Common._

	private def save(path: String): Unit = {
		val file = new File(downloadPath + path)
		if (file.isFile) {
			val parent = new File(file.getParent)
			parent.mkdirs() // create parent directory
			saveFileToDisk(file, getUrlContent( new URL( downloadPath + path ) ) ) // save to disk
		}
		else {
			file.mkdirs() // make the directory
		}
	}

	// TODO: clean up the catch
   private def saveFileToDisk(file: File, content: String): Unit = {
   		val pw = new PrintWriter(file)
    	try pw.write(content) finally pw.close()
   }

}


/**
 * This actor simply prints out the String, i.e. URL, that it's processing.
 * Note that "processing" means printing it out. TODO: actually download the String to the file system.
 */ 
class Worker extends Actor with ActorLogging {

	import Worker._
	import Common._

	def receive = {
		case DownloadUrl(relativePath) => { 
			log.info(s"saving: $relativePath")
			save(relativePath)
		}
		case fail 					   => log.error("Unknown message! content: " + fail)
	}
}