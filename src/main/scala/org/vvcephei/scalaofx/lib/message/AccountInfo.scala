package org.vvcephei.scalaofx.lib.message

object AccountInfo {
  lazy val toOfx =
      <ACCTINFOTRNRQ>
        <TRNUID>{ Util.trnuid() }</TRNUID>
        <ACCTINFORQ>
          <DTACCTUP>19980101</DTACCTUP>
        </ACCTINFORQ>
      </ACCTINFOTRNRQ>
}
