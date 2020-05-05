package com.sundogsoftware.com

import com.sundogsoftware.com.PopularHashtags.setupLogging
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object testExample {


  def setupTwitter() = {
    import scala.io.Source

    for (line <- Source.fromFile("data/twitter.txt").getLines) {
      val fields = line.split(" ")
      if (fields.length == 2) {
        System.setProperty("twitter4j.oauth." + fields(0), fields(1))
      }
    }
  }

  def main(args: Array[String]): Unit = {

    setupTwitter()

    val ssc = new StreamingContext("local[*]", "PopularHashtags", Seconds(1))

    ssc.checkpoint("/Users/sajeedbakht/Documents/apache-sparkProjects/Apache-SparkProject/twitterStreams/checkpoint")

    val tweets = TwitterUtils.createStream(ssc, None)

    val statuses = tweets.map(status => status.getText())

    ssc.start()
    ssc.awaitTermination()

    setupLogging()






  }

}
