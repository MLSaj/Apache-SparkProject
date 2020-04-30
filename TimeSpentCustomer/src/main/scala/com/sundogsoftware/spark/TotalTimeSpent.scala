package com.sundogsoftware.spark


import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object TotalTimeSpent {

  def parseLine(line:String) = {
    val fields = line.split(",")
    val customer_id = fields(0)
    val timeSpent = fields(2).toFloat
    (customer_id, timeSpent)

  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "TimeSpent")

    val lines = sc.textFile("data/customer-orders.csv")

    val rdd = lines.map(parseLine)


    val time_spent = rdd.reduceByKey((x,y) => x+y)

    val time_spent_switch_keys = time_spent.map((x) => (x._2,x._1))

    val results = time_spent_switch_keys.collect()


    for(result <- results.sorted){
      val customer_id = result._2
      val timeSpent = result._1
      println(s"customer $customer_id spent $timeSpent time")
    }







  }




}
