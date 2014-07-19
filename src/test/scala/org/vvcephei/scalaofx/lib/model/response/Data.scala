package org.vvcephei.scalaofx.lib.model.response

object Data {
  val bankStatement = """OFXHEADER:100
                        |DATA:OFXSGML
                        |VERSION:102
                        |SECURITY:NONE
                        |ENCODING:USASCII
                        |CHARSET:1252
                        |COMPRESSION:NONE
                        |OLDFILEUID:NONE
                        |NEWFILEUID:NONE
                        |
                        |<OFX>
                        |<SIGNONMSGSRSV1>
                        |<SONRS>
                        |<STATUS>
                        |<CODE>0
                        |<SEVERITY>INFO
                        |</STATUS>
                        |<DTSERVER>20140708223217.000[0:GMT]
                        |<LANGUAGE>ENG
                        |<DTACCTUP>20140708223217.000[0:GMT]
                        |<FI>
                        |<ORG>Bank of America
                        |<FID>5959
                        |</FI>
                        |<INTU.BID>XXXX
                        |<INTU.USERID>XXXX
                        |</SONRS>
                        |</SIGNONMSGSRSV1>
                        |<BANKMSGSRSV1>
                        |<STMTTRNRS>
                        |<TRNUID>0
                        |<STATUS>
                        |<CODE>0
                        |<SEVERITY>INFO
                        |</STATUS>
                        |<STMTRS>
                        |<CURDEF>USD
                        |<BANKACCTFROM>
                        |<BANKID>0000000000
                        |<ACCTID>0000000001
                        |<ACCTTYPE>CHECKING
                        |</BANKACCTFROM>
                        |<BANKTRANLIST>
                        |<DTSTART>20140501120000
                        |<DTEND>20140708120000
                        |<STMTTRN>
                        |<TRNTYPE>DEBIT
                        |<DTPOSTED>20140707120000
                        |<TRNAMT>-0.84
                        |<FITID>111
                        |<NAME>Trans Name
                        |</STMTTRN>
                        |</BANKTRANLIST>
                        |<LEDGERBAL>
                        |<BALAMT>100.10
                        |<DTASOF>20140708223217
                        |</LEDGERBAL>
                        |</STMTRS>
                        |</STMTTRNRS>
                        |</BANKMSGSRSV1>
                        |</OFX>
                        |""".stripMargin

  val ccStatement = """OFXHEADER:100
                      |DATA:OFXSGML
                      |VERSION:102
                      |SECURITY:NONE
                      |ENCODING:USASCII
                      |CHARSET:1252
                      |COMPRESSION:NONE
                      |OLDFILEUID:NONE
                      |NEWFILEUID:NONE
                      |
                      |<OFX>
                      |    <SIGNONMSGSRSV1>
                      |        <SONRS>
                      |            <STATUS>
                      |                <CODE>0
                      |                <SEVERITY>INFO
                      |            </STATUS>
                      |            <DTSERVER>20140708223356[0:GMT]
                      |            <LANGUAGE>ENG
                      |            <FI>
                      |                <ORG>Bank of America
                      |                <FID>5959
                      |            </FI>
                      |            <INTU.BID>XXXX
                      |        </SONRS>
                      |    </SIGNONMSGSRSV1>
                      |    <CREDITCARDMSGSRSV1>
                      |        <CCSTMTTRNRS>
                      |            <TRNUID>20140708223356[0:GMT]
                      |            <STATUS>
                      |                <CODE>0
                      |                <SEVERITY>INFO
                      |            </STATUS>
                      |            <CCSTMTRS>
                      |                <CURDEF>USD
                      |                <CCACCTFROM>
                      |                    <ACCTID>9999999999999
                      |                </CCACCTFROM>
                      |                <BANKTRANLIST>
                      |                    <DTSTART>20140611170000[0:GMT]
                      |                    <DTEND>20140705170000[0:GMT]
                      |                    <STMTTRN>
                      |                        <TRNTYPE>PAYMENT
                      |                        <DTPOSTED>20140611170000[0:GMT]
                      |                        <TRNAMT>-100.00
                      |                        <FITID>55555555
                      |                        <CORRECTFITID>44444444
                      |                        <CORRECTACTION>REPLACE
                      |                        <NAME>PAYEE NAME
                      |                    </STMTTRN>
                      |                </BANKTRANLIST>
                      |                <LEDGERBAL>
                      |                    <BALAMT>-100.00
                      |                    <DTASOF>20140708223356[0:GMT]
                      |                </LEDGERBAL>
                      |            </CCSTMTRS>
                      |        </CCSTMTTRNRS>
                      |    </CREDITCARDMSGSRSV1>
                      |</OFX>""".stripMargin
}
