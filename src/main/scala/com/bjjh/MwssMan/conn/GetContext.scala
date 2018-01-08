package com.bjjh.MwssMan.conn

import com.bjjh.MwssMan.config.{GetConfigFile, GetConfigMess}
import org.apache.spark.sql.SparkSession

class GetContext {

  val configFile = new GetConfigFile()

  val configMess = new GetConfigMess()

  /*
  This place writes the key-value of the config configuration
   */

  def getContext() : SparkSession.Builder = {
    SparkSession.builder().master(configMess.getMaster()).appName(configMess.getAppName())
  }

}
