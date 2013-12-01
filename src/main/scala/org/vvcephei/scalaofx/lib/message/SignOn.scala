package org.vvcephei.scalaofx.lib.message

import org.vvcephei.scalaofx.lib.model.{User, Bank}

case class SignOn(user: User, bank: Bank) {
  lazy val toOfx =
    <SIGNONMSGSRQV1>
      <SONRQ>
        <DTCLIENT>20131111224641</DTCLIENT>
        <USERID>{ user.id }</USERID>
        <USERPASS>{ user.password }</USERPASS>
        <LANGUAGE>ENG</LANGUAGE>
        <FI>
          <ORG>{ bank.fiOrg }</ORG>
          <FID>{ bank.fiId }</FID>
        </FI>
        <APPID>{ bank.appId }</APPID>
        <APPVER>{ bank.appVer }</APPVER>
      </SONRQ>
    </SIGNONMSGSRQV1>

}
