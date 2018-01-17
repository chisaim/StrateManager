package com.bjjh.MwssMan.mess

import java.text.SimpleDateFormat
import java.util.Date

class OrganTaskMessTab {

    def getTaskID(): String ={

      return "DPC" + getTime()
    }

  def getTime() : String = {
    val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
    val sdfdate = sdf.format(new Date())
    sdfdate
  }

}