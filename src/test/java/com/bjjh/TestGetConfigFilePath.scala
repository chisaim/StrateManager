package com.bjjh

import com.bjjh.MwssMan.config.{GetConfigFile, GetConfigFilePath, GetConfigMess}
import org.junit.Test

class TestGetConfigFilePath {

  val configPath = new GetConfigFilePath()

  val configFile = new GetConfigFile()

  val getdburl = new GetConfigMess()

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


  @Test
  def getConfigPath() ={
    println(configPath.getConfigPath())
    println(configPath.getMainPath())
  }
  @Test
  def getConfigFile() ={
    println(configFile.getDbConfigFile())
  }
  @Test
  def getDbConfig() = {
    println("DriverClassName:" + getDriverClassName())
    println("DbUrl:" + getDBUrl())
    println("DbUserName:" + getDbUserName())
    println("DbPassword:" + getDbPassword)
  }
  @Test
  def getOperationConfig() ={
    println("Master:" + getMaster())
    println("AppName:" + getAppName())
  }

}
