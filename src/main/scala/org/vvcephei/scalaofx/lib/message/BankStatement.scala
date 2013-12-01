package org.vvcephei.scalaofx.lib.message

import org.vvcephei.scalaofx.lib.model.Account
import org.joda.time.DateTime

case class BankStatement(account: Account, startDate: DateTime) {
  lazy val toOfx =
      <STMTTRNRQ>
        <TRNUID>{ Util.trnuid() }</TRNUID>
        <STMTRQ>
          <BANKACCTFROM>
            <BANKID>{ account.routing }</BANKID>
            <ACCTID>{ account.account }</ACCTID>
            <ACCTTYPE>{ account.`type` }</ACCTTYPE>
          </BANKACCTFROM>
          <INCTRAN>
            <DTSTART>{ Util.toDateString(startDate) }</DTSTART>
            <INCLUDE>Y</INCLUDE>
          </INCTRAN>
        </STMTRQ>
      </STMTTRNRQ>
}
