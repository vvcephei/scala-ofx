package org.vvcephei.scalaofx.lib.message

object Profile {
  val toOfx =
      <PROFTRNRQ>
        <TRNUID>{ Util.trnuid() }</TRNUID>
        <PROFRQ>
          <CLIENTROUTING>NONE</CLIENTROUTING>
          <DTPROFUP>19980101</DTPROFUP>
        </PROFRQ>
      </PROFTRNRQ>
}
