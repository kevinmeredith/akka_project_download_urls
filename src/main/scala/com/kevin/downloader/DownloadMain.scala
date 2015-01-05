package com.kevin.downloader

import akka.actor.{Props, ActorSystem}

object DownloadMain {

	val system = ActorSystem("Downloader")

	val actor = system.actorOf(Props[DownloadActor], "Downloader")

	def main(args: Array[String]) {
		actor ! "howdy!"
	}
}