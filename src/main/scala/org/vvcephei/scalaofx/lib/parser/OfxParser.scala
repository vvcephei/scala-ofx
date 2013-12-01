package org.vvcephei.scalaofx.lib.parser

import scala.xml.{Elem, XML}

object OfxParser {
  def toXml(ofx: String): Elem = {
    val tags: List[OfxLexicalItem] = OfxLexer.lexAddMissingTags(ofx)
    XML.loadString(innerToXmlString(tags).mkString)
  }

  // Pretty dumb that this is a thing...
  def toStrictOfx(elem:Elem): String = innerToXmlString(OfxLexer.lexRemoveOptionalTags(elem.toString)).mkString

  private def innerToXmlString(validXml: List[OfxLexicalItem]): List[String] = validXml match {
    case Nil => Nil
    case OpenTag(name):: rest => "<"+name+">"::innerToXmlString(rest)
    case CloseTag(name) :: rest => "</"+name+">"::innerToXmlString(rest)
    case Text(text) :: rest => text :: innerToXmlString(rest)
  }
}
