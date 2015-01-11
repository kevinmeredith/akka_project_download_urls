package com.kevin.downloader

import akka.actor.{Props, ActorSystem}
import java.net.URL

object DownloadMain {

	import Common.DOWNLOAD_URL

	val system = ActorSystem("Downloader")

	val actor = system.actorOf(Props[DownloadActor], "DownloadActor")

	def main(args: Array[String]) {
		actor ! new URL(DOWNLOAD_URL)
	}
}