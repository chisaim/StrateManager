package com.bjjh.MwssMan.main
import java.util.Properties
import com.bjjh.MwssMan.config.GetConfigMess
import com.bjjh.MwssMan.conn.GetContext

object main {

  val context = new GetContext()

  val configMess = new GetConfigMess()

  def getProp():Properties = {

    val prop = new Properties()
    prop.setProperty("user",configMess.getDbUserName())
    prop.setProperty("password",configMess.getDbPassword())
    prop
  }


  def main(args: Array[String]): Unit = {
    val session = context.getContext().getOrCreate()

    val data01 = session.read.jdbc(configMess.getDBUrl(),"customer",getProp()).select("customer_name").show()

    val data02 = session.read.jdbc(configMess.getDBUrl(),"customer",getProp()).select("contact_name").count()

//    for(column <- data01;column01 <- data02){
//
//    }


    session.close()
  }


}
