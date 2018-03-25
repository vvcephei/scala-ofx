package org.vvcephei.scalaofx.lib.model.response

import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite
import org.vvcephei.scalaofx.client.SourceClient
import org.vvcephei.scalaofx.lib.model.AccountType
import org.vvcephei.scalaofx.lib.parser.TopLevelOfxMessageParser

import scala.io.Source

class BankStatementTest extends FunSuite {
  test("can parse bank statement") {
    val statements: BankStatementResponse = SourceClient.bankStatements(Source.fromString(Data.bankStatement))
    assert(statements.errors.isEmpty)
    assert(statements.statements.length === 1)
    assert(statements.statements.head.account.routing === Some("0000000000"))
    assert(statements.statements.head.account.account === Some("0000000001"))
    assert(statements.statements.head.account.`type` === Some(AccountType.CHECKING))
    assert(statements.statements.head.currency === "USD")
    assert(statements.statements.head.availableBalance === None)
    println(statements.statements.head.startTime)
    assert(statements.statements.head.startTime === Some(new DateTime(2014, 5, 1, 12, 0, 0, DateTimeZone.UTC)))
    assert(statements.statements.head.endTime === Some(new DateTime(2014, 7, 8, 12, 0, 0, DateTimeZone.UTC)))
    assert(statements.statements.head.ledgerBalance === 100.10)
    assert(statements.statements.head.transactions.length === 1)
    assert(statements.statements.head.transactions.head.`type` === TransactionType.DEBIT)
    assert(statements.statements.head.transactions.head.posted === new DateTime(2014, 7, 7, 12, 0, 0, DateTimeZone.UTC))
    assert(statements.statements.head.transactions.head.amount === -0.84)
    assert(statements.statements.head.transactions.head.memo === None)
    assert(statements.statements.head.transactions.head.name === Some("Trans Name"))
    assert(statements.statements.head.transactions.head.transactionId === "111")
  }

  test("can parse cc statement") {
    val statements: BankStatementResponse = SourceClient.bankStatements(Source.fromString(Data.ccStatement))
    assert(statements.errors.isEmpty)
    assert(statements.statements.length === 1)
    assert(statements.statements.head.account.routing === None)
    assert(statements.statements.head.account.account === Some("9999999999999"))
    assert(statements.statements.head.account.`type` === Some(AccountType.CREDITCARD))
    assert(statements.statements.head.currency === "USD")
    assert(statements.statements.head.availableBalance === None)
    assert(
      statements.statements.head.startTime.get.getMillis ===
        new DateTime("2014-06-11T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.endTime.get.getMillis === new DateTime("2014-07-05T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.ledgerBalance === -100.00)
    assert(statements.statements.head.transactions.length === 1)
    assert(statements.statements.head.transactions.head.`type` === TransactionType.PAYMENT)
    assert(
      statements.statements.head.transactions.head.posted.getMillis ===
        new DateTime("2014-06-11T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.transactions.head.amount === -100.00)
    assert(statements.statements.head.transactions.head.memo === None)
    assert(statements.statements.head.transactions.head.name === Some("PAYEE NAME"))
    assert(statements.statements.head.transactions.head.transactionId === "55555555")
  }

  test("can parse error response with message") {
    val ofx = BankStatement.fromOfx(TopLevelOfxMessageParser.parse("<OFX>\n  <SIGNONMSGSRSV1>\n    <SONRS>\n" +
      "      <STATUS>\n" +
      "        <CODE>123</CODE>\n" +
      "        <SEVERITY>ERROR</SEVERITY>\n" +
      "        <MESSAGE>asdf</MESSAGE>\n" +
      "      </STATUS>\n      " +
      "<DTSERVER>20180325181346.396</DTSERVER>\n      <LANGUAGE>ENG</LANGUAGE>\n      " +
      "<DTACCTUP>20180325181346.396</DTACCTUP>\n      <FI>\n        <ORG>YYY</ORG>\n        " +
      "<FID>XXX</FID>\n      </FI>\n    </SONRS>\n  </SIGNONMSGSRSV1>\n</OFX>\n").ofx)

    assert(ofx.errors === Seq(BankStatementError("123", "ERROR", "asdf")))
  }

  test("can parse error response without message") {
    val ofx = BankStatement.fromOfx(TopLevelOfxMessageParser.parse("<OFX>\n  <SIGNONMSGSRSV1>\n    <SONRS>\n" +
      "      <STATUS>\n" +
      "        <CODE>123</CODE>\n" +
      "        <SEVERITY>ERROR</SEVERITY>\n" +
      "      </STATUS>\n      " +
      "<DTSERVER>20180325181346.396</DTSERVER>\n      <LANGUAGE>ENG</LANGUAGE>\n      " +
      "<DTACCTUP>20180325181346.396</DTACCTUP>\n      <FI>\n        <ORG>YYY</ORG>\n        " +
      "<FID>XXX</FID>\n      </FI>\n    </SONRS>\n  </SIGNONMSGSRSV1>\n</OFX>\n").ofx)

    assert(ofx.errors === Seq(BankStatementError("123", "ERROR", "")))
  }
}
