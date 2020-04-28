import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

class example extends  App {
  override  def main(args: Array[String]) : Unit = {
    val sparkconf = new SparkConf()
    val sc = new SparkContext(sparkconf)

  }


}
