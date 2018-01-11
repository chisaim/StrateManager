package com.bjjh

import com.bjjh.MwssMan.conn.GetContext
import org.junit.Test

class TestConvert {

  case class AAA(n:Long,h:Long)

  val context = new GetContext()


  @Test
  def localDataToRDD() = {
    val session = context.getContext().getOrCreate()

    val listOfEmployees = List(AAA(1, 1), AAA(2, 2), AAA(3, 3))

    session.sqlContext.createDataFrame(listOfEmployees)



  }

}
