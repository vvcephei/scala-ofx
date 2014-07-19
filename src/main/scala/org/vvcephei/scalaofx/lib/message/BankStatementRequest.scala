package org.vvcephei.scalaofx.lib.message

import org.vvcephei.scalaofx.lib.model.Account
import org.joda.time.DateTime

case class BankStatementRequest(account: Account, startDate: DateTime) {
  lazy val toOfx =
      <STMTTRNRQ>
        <TRNUID>{ Util.trnuid() }</TRNUID>
        <STMTRQ>
          <BANKACCTFROM>
            <BANKID>{ account.routing.get }</BANKID>
            <ACCTID>{ account.account.get }</ACCTID>
            <ACCTTYPE>{ account.`type`.get }</ACCTTYPE>
          </BANKACCTFROM>
          <INCTRAN>
            <DTSTART>{ Util.toDateString(startDate) }</DTSTART>
            <INCLUDE>Y</INCLUDE>
          </INCTRAN>
        </STMTRQ>
      </STMTTRNRQ>
}
