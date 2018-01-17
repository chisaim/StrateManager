package com.bjjh.MwssMan.mess

import java.text.SimpleDateFormat
import java.util.Date

class OrganTaskMessTab {

    def getTaskID(): String ={

      val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
      val sdfdate = sdf.format(new Date())

      return "DPC" + sdfdate
    }

  def getStartTime() : String = {
    val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
    val sdfdate = sdf.format(new Date())

    return sdfdate
  }
  def getEndTime() : String = {
    val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
    val sdfdate = sdf.format(new Date())

    return sdfdate
  }

}