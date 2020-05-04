package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec


object ItemBasedColloborativeFiltering {

  type rating = (Int,(String,Int))
  type MovieRating = (Int, Double)
  type UserRatingPair = (Int, (MovieRating, MovieRating))

  def parseLine(line:String) = {
    val fields = line.split("\t")
    val movie = fields(1)
    val user = fields(0).toInt
    val rating = fields(2).toInt

    (user, (movie,rating))

  }

  def filter_out_bad(rating:rating):Boolean = {
    val rating_value = rating._2._2
    return rating_value > 4

  }





  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "CollobFiltering")

    val lines = sc.textFile("data/test.data")


    val ratings  = lines.map(parseLine)
    //val good_ratings = ratings.filter(filter_out_bad)

    //val collect = good_ratings.collect()


    val all_movies = ratings.join(ratings)

    val collect = all_movies.collect()

    collect.foreach(println)

  }



}
