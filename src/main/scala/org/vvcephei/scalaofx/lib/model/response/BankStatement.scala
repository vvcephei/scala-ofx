package org.vvcephei.scalaofx.lib.model.response

import org.joda.time.DateTime
import org.vvcephei.scalaofx.lib.message.Util
import org.vvcephei.scalaofx.lib.model.{Account, AccountType}

import scala.xml.{Elem, Node, NodeSeq}

case class Transaction(`type`: TransactionType, posted: DateTime, amount: Double, transactionId: String, name: Option[String], memo: Option[String])

case class BankStatementResponse(statements: Seq[BankStatement], errors: Seq[BankStatementError])

case class BankStatement(currency: String, account: Account, startTime: Option[DateTime], endTime: Option[DateTime], transactions: Seq[Transaction], ledgerBalance: Double,
                         availableBalance: Option[Double])

case class BankStatementError(code: String, severity: String, message: String)

object BankStatement {
  def fromOfx(ofx: Elem): BankStatementResponse = BankStatementResponse(bankStatements(ofx) ++ ccStatements(ofx), errors(ofx))

  private def errors(ofx: Elem): Seq[BankStatementError] =
    for {
      status <- ofx \\ "STATUS"
      code <- status \ "CODE"
      severity <- status \ "SEVERITY"
      message = status \ "MESSAGE"
      if severity.text.toLowerCase == "error"
    } yield {
      BankStatementError(code.text, severity.text, message.text)
    }



  private def opt[B](mapping: (Node) => B)(node: NodeSeq) = node.headOption.map(mapping)
  private implicit def optText = opt(_.text) _
  private implicit def optNum = opt(_.text.toDouble) _
  private implicit def optAccountType = opt(n => AccountType.valueOf(n.text)) _
  private implicit def optDateTime = opt(n => Util.dateFromStringInferred(n.text)) _

  private def bankStatements(ofx: Elem): Seq[BankStatement] =
    for {
      statement <- ofx \\ "STMTRS"
      currency <- statement \ "CURDEF"
      account <- statement \ "BANKACCTFROM"
      transactionList <- statement \ "BANKTRANLIST"
      ledgerBal <- statement \ "LEDGERBAL" \ "BALAMT"
    } yield {
      BankStatement(
        currency.text,
        Account(account \ "BANKID", account \ "ACCTID", account \ "ACCTTYPE"),
        transactionList \ "DTSTART",
        transactionList \ "DTEND",
        for {
          transaction <- transactionList \ "STMTTRN"
          transactionType <- transaction \ "TRNTYPE"
          posted <- transaction \ "DTPOSTED"
          amount <- transaction \ "TRNAMT"
          id <- transaction \ "FITID"
        } yield {
          Transaction(
            TransactionType.valueOf(transactionType.text),
            Util.dateFromStringInferred(posted.text),
            amount.text.toDouble,
            id.text,
            transaction \ "NAME",
            transaction \ "MEMO"
          )
        },
        ledgerBal.text.toDouble,
        statement \ "AVAILBAL" \ "BALAMT"
      )
    }

  private def ccStatements(ofx: Elem): Seq[BankStatement] =
    for {
      statement <- ofx \\ "CCSTMTRS"
      currency <- statement \ "CURDEF"
      account <- statement \ "CCACCTFROM"
      transactionList <- statement \ "BANKTRANLIST"
      ledgerBal <- statement \ "LEDGERBAL" \ "BALAMT"
    } yield {
      BankStatement(
        currency.text,
        Account(account \ "BANKID", account \ "ACCTID", Some(AccountType.CREDITCARD)),
        transactionList \ "DTSTART",
        transactionList \ "DTEND",
        for {
          transaction <- transactionList \ "STMTTRN"
          transactionType <- transaction \ "TRNTYPE"
          posted <- transaction \ "DTPOSTED"
          amount <- transaction \ "TRNAMT"
          id <- transaction \ "FITID"
        } yield {
          Transaction(
            TransactionType.valueOf(transactionType.text),
            Util.dateFromStringInferred(posted.text),
            amount.text.toDouble,
            id.text,
            transaction \ "NAME",
            transaction \ "MEMO"
          )
        },
        ledgerBal.text.toDouble,
        statement \ "AVAILBAL" \ "BALAMT"
      )
    }
}
