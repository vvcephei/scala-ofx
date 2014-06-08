package org.vvcephei.scalaofx.client

import scala.io.Source
import org.vvcephei.scalaofx.lib.parser.TopLevelOfxMessageParser
import org.vvcephei.scalaofx.lib.model.response.{BankStatementResponse, BankStatement}

object SourceClient {
  def bankStatements(source: Source): BankStatementResponse = {
    val parsed = TopLevelOfxMessageParser parse source.mkString
    BankStatement fromOfx parsed.ofx
  }
}
