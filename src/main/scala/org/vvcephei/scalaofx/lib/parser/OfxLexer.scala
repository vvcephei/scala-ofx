package org.vvcephei.scalaofx.lib.parser

object OfxLexer {

  private sealed trait L1OfxLexicalItem

  private case class OpenTagStart() extends L1OfxLexicalItem

  private case class CloseTagStart() extends L1OfxLexicalItem

  private case class TagEnd() extends L1OfxLexicalItem

  private case class L1Text(text: String) extends L1OfxLexicalItem

  def lexAddMissingTags(string: String): List[OfxLexicalItem] = withEndTags(l2Lex(l1Lex(string)))

  def lexRemoveOptionalTags(string: String): List[OfxLexicalItem] = withoutOptionalTags(l2Lex(l1Lex(string)))

  private def withEndTags(ofx: List[OfxLexicalItem]): List[OfxLexicalItem] =
    ofx match {
      case Nil => Nil
      case OpenTag(name) :: Text(text) :: OpenTag(nextName) :: rest => OpenTag(name) :: Text(text) :: CloseTag(name) :: withEndTags(OpenTag(nextName) :: rest)
      case OpenTag(openName) :: Text(text) :: CloseTag(closeName) :: rest if openName != closeName => OpenTag(openName) :: Text(text) :: CloseTag(openName) :: withEndTags(CloseTag(closeName) :: rest)
      case tag :: rest => tag :: withEndTags(rest)
    }

  private def withoutOptionalTags(ofx: List[OfxLexicalItem]): List[OfxLexicalItem] =
    ofx match {
      case Nil => Nil
      case OpenTag(oName) :: Text(text) :: CloseTag(cName) :: rest if oName == cName => OpenTag(oName) :: Text(text) :: withoutOptionalTags(rest)
      case tag :: rest => tag :: withoutOptionalTags(rest)
    }

  private def l2Lex(l1lex: List[L1OfxLexicalItem]): List[OfxLexicalItem] = l1lex match {
    case Nil => Nil
    case OpenTagStart() :: L1Text(text) :: TagEnd() :: rest => OpenTag(text) :: l2Lex(rest)
    case CloseTagStart() :: L1Text(text) :: TagEnd() :: rest => CloseTag(text) :: l2Lex(rest)
    case L1Text(text) :: rest => Text(text) :: l2Lex(rest)
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

