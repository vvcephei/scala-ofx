package org.vvcephei.scalaofx.lib.parser

import org.scalatest.FunSuite
import scala.xml.Elem
import scala.xml.Utility.trim


class OfxParserTest extends FunSuite {
  test("to xml") {
    val parse = OfxParser.toXml("<OFX><WORDS>words words<MORE>more</OFX>")
    println(parse)
    val expected: Elem = <OFX>
      <WORDS>words words</WORDS>
      <MORE>more</MORE>
    </OFX>
    assert(trim(parse) == trim(expected))
  }

  test("to ofx") {
    val xml = <OFX>
      <WORDS>words words</WORDS>
      <MORE>more</MORE>
    </OFX>
    assert(OfxParser.toStrictOfx(xml) == """<OFX>
                                           |      <WORDS>words words
                                           |      <MORE>more
                                           |    </OFX>""".stripMargin)
  }
}
