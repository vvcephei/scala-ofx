package org.vvcephei.scalaofx.lib.message

import org.vvcephei.scalaofx.lib.model.Account

case class AccountStatement(account: Account) {
  lazy val toOfx =
    <BANKMSGSRQV1>
      <STMTTRNRQ>
        <TRNUID>{ Util.trnuid() }</TRNUID>
        <STMTRQ>
          <BANKACCTFROM>
            <BANKID> { account.routing } </BANKID>
            <ACCTID> { account.account } </ACCTID>
            <ACCTTYPE> { account.`type`.toString } </ACCTTYPE>
          </BANKACCTFROM>
          <INCTRAN>
            <INCLUDE>Y</INCLUDE>
          </INCTRAN>
        </STMTRQ>
      </STMTTRNRQ>
    </BANKMSGSRQV1>

}
