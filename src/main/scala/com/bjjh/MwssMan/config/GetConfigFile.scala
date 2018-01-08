package com.bjjh.MwssMan.config

class GetConfigFile {

  val configFilePath = new GetConfigFilePath()

  def getDbConfigFile() = {
    configFilePath.getConfigPath() + "\\dbConfig-env.xml"
  }

  def getOperationFile() = {
    configFilePath.getConfigPath() + "\\operation-env.xml"
  }

}
