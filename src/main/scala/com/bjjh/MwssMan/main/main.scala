package com.bjjh.MwssMan.main

import java.util.Properties
import java.util.concurrent.atomic.AtomicInteger

import com.bjjh.MessMan.util.ConvertAndUtil
import com.bjjh.MwssMan.config.GetConfigMess
import com.bjjh.MwssMan.conn.GetContext
import com.bjjh.MwssMan.mess.OrganTaskMessTab
import org.apache.spark.sql.{DataFrame, Row, SaveMode}
import org.apache.spark.sql.types._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object main {

  val context = new GetContext()

  val configMess = new GetConfigMess()

  val taskInfo = new OrganTaskMessTab()

  val util = new ConvertAndUtil()

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
    val strategyList = data01.collectAsList()
    val messageIterator = data02.collectAsList().iterator()
    var content = new ArrayBuffer[Row]()
    val content1 = new ArrayBuffer[ArrayBuffer[Row]]()

    val hitNum = 0
    val hitSum = 0



    while (strategyIterator.hasNext) {
      val strategy = strategyIterator.next().getString(0)

      while (messageIterator.hasNext) {
        val message = messageIterator.next().getString(0)
        if ((strategy.r() findAllIn message).nonEmpty) {
          content += (Row(message, strategy.toString(), hitNum + 1, hitSum + 1))
        } else {
          content += (Row(message, strategy.toString(), hitNum + 0, hitSum + 1))
        }
        content1 += content
      }
    }


    val resultSchema: StructType = StructType(mutable.Seq(
      //      StructField("taskInfo", StringType, nullable = false),
      StructField("keyword", StringType, nullable = false),
      StructField("message", StringType, nullable = false),
      StructField("hitResult", IntegerType, nullable = false),
      StructField("SumResult", IntegerType, nullable = false)
    ))

    val rdd = session.sparkContext.makeRDD(content.toList)

    val resultTable = session.sqlContext.createDataFrame(rdd, resultSchema)

    //    resultTable.write.mode(SaveMode.Append).jdbc(configMess.getDBUrl(), "target", getProp())

    session.close()
  }

}
