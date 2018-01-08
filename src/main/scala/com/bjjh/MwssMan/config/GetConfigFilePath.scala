package com.bjjh.MwssMan.config

class GetConfigFilePath {

  def getMainPath() = {

    System.getProperty("user.dir")
  }

  def getConfigPath() = {

    getMainPath() + "\\conf"
  }

  def getLogPath() = {
    getMainPath() + "\\log"
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
