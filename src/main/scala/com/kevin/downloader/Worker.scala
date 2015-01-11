package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import java.io.{File, PrintWriter}
import java.net.URL

object Worker {
	val downloadPath: String = "output/download_dir/"

	import Common._

	private def save(parentPath: String, relativePath: String): Unit = {
		val file   = new File(downloadPath + relativePath)
		val parent = new File(file.getParent)
		parent.mkdirs() // create parent directory
		val urlToDownload = new URL( parentPath + relativePath ) 
		saveFileToDisk(file, getUrlContent( urlToDownload ) ) // save to disk		
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
		case DownloadUrl(parentPath, relativePath) => { 
			log.info(s"saving: $parentPath$relativePath")
			save(parentPath, relativePath)
		}
		case fail 					   			   => log.error("Unknown message! content: " + fail)
	}
}