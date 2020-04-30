package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object MostPopularMovie {

  def parseLine(line:String) = {
    val fields = line.split("\t")
    val movie = fields(1)


    (movie)

  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "MostPopularMovie")

    val lines = sc.textFile("data/ml-100k/u.data")
    val ratings  = lines.map(parseLine)

    //val ratings = lines.map(x => x.toString().split("\t")(1))

    val movies = ratings.map(x => (x,1)).reduceByKey( (x,y) => x + y )

    val movies_sorted = movies.map( x => (x._2, x._1) ).sortByKey()

    //val popularMovie = movies.reduceByKey((x,y) => (x+y))

    //val flipped = popularMovie.map(x => (x._2, x._1))

    val results = movies_sorted.collect()

    results.foreach(println)







  }



}
