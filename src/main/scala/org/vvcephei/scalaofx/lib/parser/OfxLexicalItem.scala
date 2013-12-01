package org.vvcephei.scalaofx.lib.parser

sealed trait OfxLexicalItem

case class OpenTag(name: String) extends OfxLexicalItem

case class CloseTag(name: String) extends OfxLexicalItem

case class Text(text: String) extends OfxLexicalItem
