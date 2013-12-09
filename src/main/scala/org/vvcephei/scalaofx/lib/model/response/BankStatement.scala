package org.vvcephei.scalaofx.lib.model.response

import org.vvcephei.scalaofx.lib.model.{AccountType, Account}
import org.joda.time.DateTime
import scala.xml.Elem
import org.vvcephei.scalaofx.lib.message.Util

case class Transaction(`type`: TransactionType, posted: DateTime, amount: Double, transactionId: String, name: Option[String], memo: Option[String])

case class BankStatementResponse(statements: Seq[BankStatement], errors: Seq[BankStatementError])

case class BankStatement(currency: String, account: Account, startTime: DateTime, endTime: Option[DateTime], transactions: Seq[Transaction], ledgerBalance: Double, availableBalance: Double)

case class BankStatementError(code: String, severity: String, message: String)

object BankStatement {
  def fromOfx(ofx:Elem): BankStatementResponse = BankStatementResponse(statements(ofx), errors(ofx))

  def errors(ofx: Elem): Seq[BankStatementError] =
    for {
      status <- ofx \\ "STATUS"
      code <- status \ "CODE"
      severity <- status \ "SEVERITY"
      message <- status \ "MESSAGE"
      if severity.text.toLowerCase == "error"
    } yield {
      BankStatementError(code.text, severity.text, message.text)
    }

  def statements(ofx: Elem): Seq[BankStatement] =
    for {
      statement <- ofx \\ "STMTRS"
      currency <- statement \ "CURDEF"
      account <- statement \ "BANKACCTFROM"
      bankId <- account \ "BANKID"
      accountId <- account \ "ACCTID"
      accountType <- account \ "ACCTTYPE"
      transactionList <- statement \ "BANKTRANLIST"
      statementStart <- transactionList \ "DTSTART"
      statementEnd = (transactionList \ "DTEND").map(_.text).headOption
      ledgerBal <- statement \ "LEDGERBAL" \ "BALAMT"
      availableBal <- statement \ "AVAILBAL" \ "BALAMT"
    } yield {
      BankStatement(
        currency.text,
        Account(bankId.text, accountId.text, AccountType.valueOf(accountType.text)),
        Util.fromDateTimeWithZoneString(statementStart.text),
        statementEnd map Util.fromDateTimeWithZoneString,
        for {
          transaction <- transactionList \ "STMTTRN"
          transactionType <- transaction \ "TRNTYPE"
          posted <- transaction \ "DTPOSTED"
          amount <- transaction \ "TRNAMT"
          id <- transaction \ "FITID"
          name = (transaction \ "NAME").map(_.text).headOption
          memo = (transaction \ "MEMO").map(_.text).headOption
        } yield {
          Transaction(
            TransactionType.valueOf(transactionType.text),
            Util.fromDateTimeString(posted.text),
            amount.text.toDouble,
            id.text,
            name,
            memo
          )
        },
        ledgerBal.text.toDouble,
        availableBal.text.toDouble
      )
    }
}
