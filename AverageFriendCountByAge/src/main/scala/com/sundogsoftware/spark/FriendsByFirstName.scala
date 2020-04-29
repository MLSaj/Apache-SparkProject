package com.sundogsoftware.spark
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object FriendsByFirstName {

  def parseline(line: String): (String, Int) = {
    val fields = line.split(",")
    val name = fields(1).toString
    val numFriends = fields(3).toInt
    (name,numFriends)

  }

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", appName = "FriendsByName")

    val lines = sc.textFile("data/fakefriends.csv")

    val rdd = lines.map(parseline)

    val totalsByName = rdd.mapValues(x => (x,1)).reduceByKey( (x,y) => (x._1 + y._1, x._2 + y._2))

    val averagesByName = totalsByName.mapValues(x => x._1/x._2)

    val results = averagesByName.collect()

    results.sorted.foreach(println)



  }



}