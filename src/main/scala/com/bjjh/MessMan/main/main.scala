package com.bjjh.MessMan.main

import java.sql.{DriverManager}

import com.bjjh.MessMan.config.GetConfigMess
import com.bjjh.MessMan.conn.GetContext
import com.bjjh.MessMan.mess.OrganTaskMessTab
import org.apache.spark.sql.{Row, SaveMode}
import org.apache.spark.sql.types._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object main {

  val context = new GetContext()

  val configMess = new GetConfigMess()

  val taskInfo = new OrganTaskMessTab()

  def main(args: Array[String]): Unit = {

    val session = context.getContext().getOrCreate()

    val startTime = System.currentTimeMillis()

    Class.forName(configMess.getDriverClassName()).newInstance()

    val conn = DriverManager.getConnection(configMess.getDBUrl(), configMess.getDbUserName(), configMess.getDbPassword())

    val strategies = conn.prepareStatement("select " + configMess.getTab1col() + " from " + configMess.getTabName1())
    val messages = conn.prepareStatement("select " + configMess.getTab2col() + " from " + configMess.getTabName2())

    val strategy = strategies.executeQuery()
    val message = messages.executeQuery()

    val strategyContent = ArrayBuffer[String]()
    val messageContent = ArrayBuffer[String]()
    //    val hitArray = ArrayBuffer[Int]()

    var resultContent = new ArrayBuffer[Row]()
    var messContent = new ArrayBuffer[Row]()

    while (strategy.next()) {
      strategyContent += (strategy.getString(configMess.getTab1col()))
    }
    while (message.next()) {
      messageContent += (message.getString(configMess.getTab2col()))
    }

    strategy.close()
    message.close()
    strategies.close()
    messages.close()
    conn.close()

    /*
        val data01 = session.read.jdbc(configMess.getDBUrl(), configMess.getTabName1(), configMess.getProp()).select(configMess.getTab1col())
        val data02 = session.read.jdbc(configMess.getDBUrl(), configMess.getTabName2(), configMess.getProp()).select(configMess.getTab2col())

        val strategyIterator = data01.collectAsList().iterator()
        val strategyList = data01.collect()
        val messageIterator = data02.collectAsList().iterator()
        val content1 = new ArrayBuffer[ArrayBuffer[Row]]()
        val cast = session.sparkContext.broadcast(strategyList)

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
    */

    val taskMessSchema: StructType = StructType(mutable.Seq(
      StructField("taskID", StringType, nullable = false),
      StructField("keywordNum", IntegerType, nullable = false),
      StructField("messageNum", IntegerType, nullable = false),
      StructField("startTime", StringType, nullable = false),
      StructField("endTime", StringType, nullable = false),
      StructField("elapsedTime", StringType, nullable = false)
    ))

    val resultSchema: StructType = StructType(mutable.Seq(
      StructField("taskID", StringType, nullable = false),
      StructField("keyword", StringType, nullable = false),
      StructField("message", StringType, nullable = false)
      //      StructField("hitResult", IntegerType, nullable = false),
      //      StructField("SumResult", IntegerType, nullable = false)
    ))

    for (strate <- strategyContent) {
      for (mess <- messageContent) {
        if ((strate.r() findAllIn mess).nonEmpty) {
          resultContent += (Row(taskInfo.getTaskID(), strate, mess))
        }
      }
    }
    messContent += (Row(taskInfo.getTaskID(),
      strategyContent.size,
      messageContent.size,
      taskInfo.getTime(),
      taskInfo.getTime(),
      String.valueOf(endStart - startTime) + "ms"))

    val taskMessRdd = session.sparkContext.makeRDD(messContent.toList)
    val resultRdd = session.sparkContext.makeRDD(resultContent.toList)

    val taskMessTable = session.sqlContext.createDataFrame(taskMessRdd, taskMessSchema)
    val resultTable = session.sqlContext.createDataFrame(resultRdd, resultSchema)
    taskMessTable.write.mode(SaveMode.Append).jdbc(configMess.getDBUrl(), "taskMessTable", configMess.getProp())
    resultTable.write.mode(SaveMode.Append).jdbc(configMess.getDBUrl(), "resultTable", configMess.getProp())

    val endStart = System.currentTimeMillis()


    session.close()

  }

}
