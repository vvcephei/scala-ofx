package org.vvcephei.scalaofx.lib.parser

import org.scalatest.FunSuite
import scala.xml.PrettyPrinter


class TopLevelOfxMessageParserTest extends FunSuite {
  test("TopLevelParser") {
    val ppr = new PrettyPrinter(80, 2)
    val parse = TopLevelOfxMessageParser.parse(Data.result1)
    assert(ppr.format(parse.ofx) == ppr.format(Data.ofx1p))
  }

  test("to strict ofx") {
    assert(OfxParser.toStrictOfx(Data.ofx1p) == Data.ofx1pofx)
  }
}
