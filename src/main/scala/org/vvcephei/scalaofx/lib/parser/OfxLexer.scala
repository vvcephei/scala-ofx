package org.vvcephei.scalaofx.lib.parser

object OfxLexer {

  private sealed trait L1OfxLexicalItem

  private case class OpenTagStart() extends L1OfxLexicalItem

  private case class CloseTagStart() extends L1OfxLexicalItem

  private case class TagEnd() extends L1OfxLexicalItem

  private case class L1Text(text: String) extends L1OfxLexicalItem

  def lexAddMissingTags(string: String): List[OfxLexicalItem] = withEndTags(l2Lex(l1Lex(string)))

  def lexRemoveOptionalTags(string: String): List[OfxLexicalItem] = withoutOptionalTags(l2Lex(l1Lex(string)))

  private def withEndTags(ofx: List[OfxLexicalItem], result: List[OfxLexicalItem] = Nil): List[OfxLexicalItem] =
    ofx match {
      case Nil => result.reverse
      case OpenTag(name) :: Text(text) :: OpenTag(nextName) :: rest =>
        withEndTags(OpenTag(nextName) :: rest, CloseTag(name) :: Text(text) :: OpenTag(name) :: result)
      case OpenTag(openName) :: Text(text) :: CloseTag(closeName) :: rest if openName != closeName =>
        withEndTags(CloseTag(closeName) :: rest, CloseTag(openName) :: Text(text) :: OpenTag(openName) :: result)
      case tag :: rest =>
        withEndTags(rest, tag :: result)
    }

  private def withoutOptionalTags(ofx: List[OfxLexicalItem], result: List[OfxLexicalItem] = Nil): List[OfxLexicalItem] =
    ofx match {
      case Nil => result.reverse
      case OpenTag(oName) :: Text(text) :: CloseTag(cName) :: rest if oName == cName =>
        withoutOptionalTags(rest, Text(text) :: OpenTag(oName) :: result)
      case tag :: rest => withoutOptionalTags(rest, tag :: result)
    }

  private def l2Lex(l1lex: List[L1OfxLexicalItem], result: List[OfxLexicalItem] = Nil): List[OfxLexicalItem] = l1lex match {
    case Nil => result.reverse
    case OpenTagStart() :: L1Text(text) :: TagEnd() :: rest => l2Lex(rest, OpenTag(text) :: result)
    case CloseTagStart() :: L1Text(text) :: TagEnd() :: rest => l2Lex(rest, CloseTag(text) :: result)
    case L1Text(text) :: rest => l2Lex(rest, Text(text) :: result)
    case _ => throw new IllegalArgumentException("apparently invalid input: %s".format(l1lex))
  }

  private def l1Lex(string: String): List[L1OfxLexicalItem] = {
    var openTag = false
    var currentText = List[Char]()
    val nested =
      for (char <- string) yield {
        char match {
          case '<' =>
            openTag = true
            if (currentText.isEmpty)
            /*emit*/ Nil
            else {
              val emit = L1Text(currentText.reverse.mkString)
              currentText = Nil
              emit :: Nil
            }
          case '/' if openTag =>
            openTag = false
            /*emit*/ CloseTagStart() :: Nil
          case '/' =>
            currentText = char :: currentText
            /*emit nothing yet*/ Nil
          case '>' if !currentText.isEmpty =>
            val text = currentText.reverse.mkString
            currentText = Nil
            /*emit*/ L1Text(text) :: TagEnd() :: Nil
          case _ if openTag =>
            openTag = false
            currentText = char :: currentText
            /*emit*/ OpenTagStart() :: Nil
          case _ =>
            currentText = char :: currentText
            /*emit*/ Nil
        }
      }
    nested.flatten.toList
  }
}

