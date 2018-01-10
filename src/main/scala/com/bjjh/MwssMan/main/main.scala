package com.bjjh.MwssMan.main

import java.util.Properties

import com.bjjh.MwssMan.config.GetConfigMess
import com.bjjh.MwssMan.conn.GetContext
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrameReader, Row}
import org.apache.spark.sql.types._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object main {

  val context = new GetContext()

  val configMess = new GetConfigMess()

  def getProp(): Properties = {

    val prop = new Properties()
    prop.setProperty("user", configMess.getDbUserName())
    prop.setProperty("password", configMess.getDbPassword())
    prop
  }


  def main(args: Array[String]): Unit = {
    val session = context.getContext().getOrCreate()

    val data01 = session.read.jdbc(configMess.getDBUrl(), configMess.getTabName1(), getProp()).select(configMess.getTab1col())

    val data02 = session.read.jdbc(configMess.getDBUrl(), configMess.getTabName2(), getProp()).select(configMess.getTab2col())

    val strategyIterator = data01.collectAsList().iterator()

    val messageIterator = data02.collectAsList().iterator()

    var hitArray = new ArrayBuffer[Long]()
    val hitArrayContent = new ArrayBuffer[Long]()
    var missArray = new ArrayBuffer[Long]()
    val missArrayContent = new ArrayBuffer[Long]()

    val aa = Seq(ResultObj)

    while (strategyIterator.hasNext) {
      val strategy = strategyIterator.next().getString(0).r()
      while (messageIterator.hasNext) {
        val message = messageIterator.next().getString(0)
        if ((strategy findAllIn message).nonEmpty) {
          hitArray.+=(1)
        } else {
          missArray.+=(0)
        }
      }
      hitArrayContent += (hitArray.size)
      missArrayContent += (missArray.size)



    }

    val resultSchema: StructType = StructType(mutable.Seq(
      StructField("hitResult", StringType, nullable = false),
      StructField("missResult", StringType, nullable = false)
    ))

    val rdd = session.sparkContext.makeRDD(hitArrayContent.toList)

//    val resultTable = session.sqlContext.createDataFrame(rdd, resultSchema)

    println("成功匹配结果:" + hitArray)
    println("失败匹配结果:" + missArray)
    println("成功匹配结果容器:" + hitArrayContent)
    println("失败匹配结果容器:" + missArrayContent)
    println("hitContent:" + hitArrayContent.size)
    println("missContent:" + missArrayContent.size)

    session.close()
  }

  case class ResultObj(h: Long, m: Long)

}
