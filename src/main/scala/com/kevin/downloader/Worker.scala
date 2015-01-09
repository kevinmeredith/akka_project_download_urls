package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}
import java.io.{File, PrintWriter}

object Worker {
	val downloadPath: String = "~/Workspace/Temp/Downloader/"

	def save(path: String): Unit = {
		val file = new File(downloadPath + path)
		if (file.isFile) {
			file.mkdirs(file.getParent) // create parent directory
			saveFileToDisk(file) // save to disk

		}
		else {
			file.mkdirs() // make the directory
		}
	}

	def downloadRemoteFile(path: String): String = {
		
	}

	// TODO: clean up the catch
   def saveFileToDisk(file: File, content: String): Unit = {
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

	def receive = {
		case DownloadUrl(relativePath) => save(relativePath)
		case fail 					   => log.error("Unknown message! content: " + fail)
	}
}