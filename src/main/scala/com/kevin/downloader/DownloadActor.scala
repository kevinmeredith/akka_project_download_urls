package com.kevin.downloader

import akka.actor.{Actor, Props, ActorSystem}

class DownloadActor extends Actor {

	def receive  = {
		case x => println("got something: " + x)
	}
	
}