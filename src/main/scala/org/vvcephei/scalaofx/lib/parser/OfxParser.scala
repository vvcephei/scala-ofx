package org.vvcephei.scalaofx.lib.parser

import scala.xml.{Elem, XML}

object OfxParser {
  def toXml(ofx: String): Elem =
    XML.loadString(innerToXmlString(OfxLexer.lexAddMissingTags(ofx)).mkString)

  // Pretty dumb that this is a thing...
  def toStrictOfx(elem: Elem): String = innerToXmlString(OfxLexer.lexRemoveOptionalTags(elem.toString)).mkString

  //TODO: Stream processing is probaly better
  private def innerToXmlString(validXml: List[OfxLexicalItem], result: List[String] = Nil): List[String] = validXml match {
    case Nil => result.reverse
    case OpenTag(name) :: rest => innerToXmlString(rest, "<" + name + ">" :: result)
    case CloseTag(name) :: rest => innerToXmlString(rest, "</" + name + ">" :: result)
    case Text(text) :: rest => innerToXmlString(rest, text :: result)
  }
}
