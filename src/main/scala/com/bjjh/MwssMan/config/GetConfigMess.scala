package com.bjjh.MwssMan.config

import org.dom4j.{Document, Element}
import org.dom4j.io.SAXReader

class GetConfigMess {

  val configFile = new GetConfigFile()

  def getConfigFileDocument(filePath: String): Document = {
    val reader = new SAXReader()
    reader.read(filePath)
  }

  def getRootElement(doc: Document): Element = {
    doc.getRootElement
  }

  def getTagNameInDoc(document: Document, tagName: String): Element = {
    getRootElement(document).element(tagName)
  }

  def getElementTextValue(document: Document, tagName: String): String = {
    getRootElement(document).elementText(tagName)
  }

  /*
  This place writes the key-value of the config configuration

    def getAttributeValueInTag(document: Document,tagName:String) :String = {
      getTagNameInDoc(document,"config").attribute("key1").getValue
    }
  */
}
