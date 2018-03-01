package com.bjjh.MessMan.config

class GetConfigFilePath {

  def getMainPath() = {

    System.getProperty("user.dir")
  }

  def getConfigPath() = {

//    getMainPath() + "/config"
    getMainPath() + "/src/main/resources"
  }

  def getLogPath() = {
    getMainPath() + "/log"
  }
  /*
    def getLibPath() = {
      getMainPath + "\\lib"
    }

    def getBinPath() = {
      getMainPath() + "\\sbin"
    }
  */
}
