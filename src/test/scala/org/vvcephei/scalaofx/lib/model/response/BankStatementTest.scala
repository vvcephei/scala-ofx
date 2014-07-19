package org.vvcephei.scalaofx.lib.model.response

import org.joda.time.DateTime
import org.scalatest.FunSuite
import org.vvcephei.scalaofx.client.SourceClient
import org.vvcephei.scalaofx.lib.model.AccountType

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
    assert(statements.statements.head.startTime === Some(new DateTime("2014-05-01T12:00:00.000-05:00")))
    assert(statements.statements.head.endTime === Some(new DateTime("2014-07-08T12:00:00.000-05:00")))
    assert(statements.statements.head.ledgerBalance === 100.10)
    assert(statements.statements.head.transactions.length === 1)
    assert(statements.statements.head.transactions.head.`type` === TransactionType.DEBIT)
    assert(statements.statements.head.transactions.head.posted === new DateTime("2014-07-07T12:00:00.000-05:00"))
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
    assert(statements.statements.head.startTime.get.getMillis === new DateTime("2014-06-11T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.endTime.get.getMillis === new DateTime("2014-07-05T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.ledgerBalance === -100.00)
    assert(statements.statements.head.transactions.length === 1)
    assert(statements.statements.head.transactions.head.`type` === TransactionType.PAYMENT)
    assert(statements.statements.head.transactions.head.posted.getMillis === new DateTime("2014-06-11T12:00:00.000-05:00").getMillis)
    assert(statements.statements.head.transactions.head.amount === -100.00)
    assert(statements.statements.head.transactions.head.memo === None)
    assert(statements.statements.head.transactions.head.name === Some("PAYEE NAME"))
    assert(statements.statements.head.transactions.head.transactionId === "55555555")
  }

}
