package org.vvcephei.scalaofx.lib.parser

import org.scalatest.FunSuite


class OfxLexerTest extends FunSuite{
  test("OfxLexer.lexAddMissingTags") {
        val lex: List[OfxLexicalItem] = OfxLexer.lexAddMissingTags("<OFX>words words</OFX>")
        assert(lex == List(OpenTag("OFX"), Text("words words"), CloseTag("OFX")))
  }
}
