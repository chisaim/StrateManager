package com.bjjh.MwssMan.conn

import com.bjjh.MwssMan.config.{GetConfigFile, GetConfigMess}
import org.apache.spark.sql.SparkSession

class GetContext {

  val configFile = new GetConfigFile()

  val configMess = new GetConfigMess()

  def getDBUrl(): String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getDbConfigFile()),"dbUrl")
  }

  def getDriverClassName(): String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getDbConfigFile()),"driverClassName")
  }

  def getDbUserName(): String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getDbConfigFile()),"dbUserName")

  }

  def getDbPassword(): String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getDbConfigFile()),"dbPassword")
  }

  def getMaster():String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getOperationFile()),"master")
  }

  def getAppName():String = {
    configMess.getElementTextValue(configMess.getConfigFileDocument(configFile.getOperationFile()),"appName")
  }

  /*
  This place writes the key-value of the config configuration
   */

  def getContext() : SparkSession.Builder = {
    SparkSession.builder().master(getMaster()).appName(getAppName())
  }

}
