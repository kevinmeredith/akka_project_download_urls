package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem, ActorLogging}

/**
 * This actor simply prints out the String, i.e. URL, that it's processing.
 * Note that "processing" means printing it out. TODO: actually download the String to the file system.
 */ 
class Worker extends Actor with ActorLogging {

	def receive = {
		case x => log.info(s"processing : $x")
	}
}