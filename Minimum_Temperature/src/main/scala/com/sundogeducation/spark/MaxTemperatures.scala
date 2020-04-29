package com.sundogeducation.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

object MaxTemperatures {

  def parseLine(line:String): (String,String, Float) = {
    val fields = line.split(",")
    val station_id = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (station_id,entryType, temperature)

  }

  def main(args: Array[String]) = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "maxTemperature")

    val lines = sc.textFile("data/1800.csv")

    val rdd = lines.map(parseLine)

    val max_temps = rdd.filter(x => x._2 == "TMAX")

    val key_value_temps = max_temps.map(x => (x._1, x._3.toFloat))

    val maximumTemperatures = key_value_temps.reduceByKey((x,y) => max(x,y))

    val results = maximumTemperatures.collect()

    for (result <- results.sorted) {
      val station = result._1
      val temp = result._2
      val formattedTemp = f"$temp%.2f F"
      println(s"$station maximum temperature: $formattedTemp")
    }




  }

}
