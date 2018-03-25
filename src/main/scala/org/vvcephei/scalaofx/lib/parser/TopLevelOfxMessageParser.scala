package org.vvcephei.scalaofx.lib.parser

import scala.xml.Elem

case class OfxMessage(header: Map[String, String], ofx: Elem)

object TopLevelOfxMessageParser {
  private val headerline = """(\p{Alpha}+):([^\r\n]*)[\r\n]*""".r("headerkey", "headerval")
  private val blankLine = """^\s*$""".r
  private val sgmlStart = """^\s*<""".r

  private def parseHeaderLine(line: String) =
    for (mat <- headerline.findAllMatchIn(line)) yield {
      (mat group "headerkey") -> (mat group "headerval")
    }

  def parse(msg: String): OfxMessage = {
    val lines = msg.split("""\n|\r\n""").toStream
    var state = ParseState.HEADER
    var headers = Map[String, String]()
    val ofxlines =
      for (line <- lines) yield {
        if (headerline.findFirstMatchIn(line).isDefined && state == ParseState.HEADER) state = ParseState.HEADER
        else if (blankLine.findFirstMatchIn(line).isDefined && (state == ParseState.HEADER || state == ParseState.BLANKS)) state = ParseState.BLANKS
        else if ((sgmlStart.findFirstMatchIn(line).isDefined && state != ParseState.OFX) || state == ParseState.OFX) state = ParseState.OFX
        else throw new IllegalStateException("Illegal top level parser state.")

        state match {
          case ParseState.HEADER =>
            headers = headers ++ parseHeaderLine(line)
            ""
          case ParseState.BLANKS =>
            // do nothing
            ""
          case ParseState.OFX =>
            line
        }
      }

    val ofxString = ofxlines.map(_.trim) mkString ""

    OfxMessage(headers, OfxParser toXml ofxString)
  }
}
