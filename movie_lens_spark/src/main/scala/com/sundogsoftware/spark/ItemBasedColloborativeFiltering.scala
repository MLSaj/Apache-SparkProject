package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec


object ItemBasedColloborativeFiltering {

  def parseLine(line:String) = {
    val fields = line.split("\t")
    val movie = fields(1)
    val user = fields(0)
    val rating = fields(2)

    (user, (movie,rating))

  }



  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "CollobFiltering")

    val lines = sc.textFile("data/test.data")


    val ratings  = lines.map(parseLine)

    val all_movies = ratings.join(ratings)

    val collect = all_movies.collect()

    collect.foreach(println)

  }



}
