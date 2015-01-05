package com.kevin.downloader

import akka.actor.{Props, ActorSystem}
import java.net.URL

object DownloadMain {

	val system = ActorSystem("Downloader")

	val actor = system.actorOf(Props[DownloadActor], "Downloader")

	def main(args: Array[String]) {
		actor ! new URL("https://www.google.com")
	}
}