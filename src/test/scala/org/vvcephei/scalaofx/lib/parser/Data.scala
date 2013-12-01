package org.vvcephei.scalaofx.lib.parser

object Data {
  val result1 = """OFXHEADER:100
                  |DATA:OFXSGML
                  |VERSION:102
                  |SECURITY:NONE
                  |ENCODING:USASCII
                  |CHARSET:1252
                  |COMPRESSION:NONE
                  |OLDFILEUID:NONE
                  |NEWFILEUID:NONE
                  |
                  |<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>0<SEVERITY>INFO<MESSAGE>The operation succeeded.</STATUS><DTSERVER>20131110152328
                  |.855[-6:CST]<LANGUAGE>ENG<FI><ORG>EXAMPLE<FID>12345</FI></SONRS></SIGNONMSGSRSV1><PROFMSGSRSV1><PROFTRNRS><TRNUID>10001<STATUS><CODE>0<SEVERITY>INFO</STATUS><PROFRS><MSGSETLIST
                  |><SIGNONMSGSET><SIGNONMSGSETV1><MSGSETCORE><VER>1<URL>https://www.example.com<OFXSEC>NONE<TRANSPSEC>Y<SIGNONREALM>Standard<LANGUAGE>ENG<SYNCMODE>LITE<RESPFILEER>Y<INTU
                  |.TIMEOUT>180</MSGSETCORE></SIGNONMSGSETV1></SIGNONMSGSET><SIGNUPMSGSET><SIGNUPMSGSETV1><MSGSETCORE><VER>1<URL>https://www.example
                  |.com<OFXSEC>NONE<TRANSPSEC>Y<SIGNONREALM>Standard<LANGUAGE>ENG<SYNCMODE>LITE<RESPFILEER>Y<INTU.TIMEOUT>180</MSGSETCORE><WEBENROLL><URL>www.example
                  |.com</WEBENROLL><CHGUSERINFO>N<AVAILACCTS>Y<CLIENTACTREQ>N</SIGNUPMSGSETV1></SIGNUPMSGSET><BANKMSGSET><BANKMSGSETV1><MSGSETCORE><VER>1<URL>https://www.example
                  |.com<OFXSEC>NONE<TRANSPSEC>Y<SIGNONREALM>Standard<LANGUAGE>ENG<SYNCMODE>FULL<RESPFILEER>Y<INTU
                  |.TIMEOUT>180</MSGSETCORE><CLOSINGAVAIL>Y<XFERPROF><PROCDAYSOFF>SATURDAY<PROCDAYSOFF>SUNDAY<PROCENDTM>140000<CANSCHED>N<CANRECUR>N<CANMODXFERS>N<CANMODMDLS>N<MODELWND>2<DAYSWITH>2
                  |<DFLTDAYSTOPAY>1</XFERPROF><EMAILPROF><CANEMAIL>N<CANNOTIFY>N</EMAILPROF></BANKMSGSETV1></BANKMSGSET><BILLPAYMSGSET><BILLPAYMSGSETV1><MSGSETCORE><VER>1<URL>https://www.example
                  |.com<OFXSEC>NONE<TRANSPSEC>Y<SIGNONREALM>Standard<LANGUAGE>ENG<SYNCMODE>FULL<RESPFILEER>Y<INTU
                  |.TIMEOUT>180</MSGSETCORE><DAYSWITH>0<DFLTDAYSTOPAY>0<XFERDAYSWITH>0<XFERDFLTDAYSTOPAY>0<PROCDAYSOFF>SATURDAY<PROCDAYSOFF>SUNDAY<PROCENDTM>120000
                  |.000[-6:CST]<MODELWND>0<POSTPROCWND>0<STSVIAMODS>Y<PMTBYADDR>Y<PMTBYXFER>N<PMTBYPAYEEID>N<CANADDPAYEE>Y<HASEXTDPMT>N<CANMODPMTS>Y<CANMODMDLS>N<DIFFFIRSTPMT>N<DIFFLASTPMT>N
                  |</BILLPAYMSGSETV1></BILLPAYMSGSET><PROFMSGSET><PROFMSGSETV1><MSGSETCORE><VER>1<URL>https://www.example
                  |.com<OFXSEC>NONE<TRANSPSEC>Y<SIGNONREALM>Standard<LANGUAGE>ENG<SYNCMODE>LITE<RESPFILEER>Y<INTU
                  |.TIMEOUT>180</MSGSETCORE></PROFMSGSETV1></PROFMSGSET></MSGSETLIST><SIGNONINFOLIST><SIGNONINFO><SIGNONREALM>Standard<MIN>4<MAX>20<CHARTYPE>ALPHAORNUMERIC<CASESEN>N<SPECIAL>Y
                  |<SPACES>Y<PINCH>N<CHGPINFIRST>N</SIGNONINFO></SIGNONINFOLIST><DTPROFUP>20131110212329.590<FINAME>Example<ADDR1>PO Box 1234<ADDR2>Any, OK 74123
                  |<CITY>ANY<STATE>OK<POSTALCODE>741231234<COUNTRY>USA<CSPHONE>(555) 555-5555<TSPHONE>555-555-5555<URL>https://www.example.com/example<EMAIL>support@example.org<INTU
                  |.BROKERID>example.com</PROFRS></PROFTRNRS></PROFMSGSRSV1></OFX>""".stripMargin
  val ofx1p = <OFX>
    <SIGNONMSGSRSV1>
      <SONRS>
        <STATUS>
          <CODE>0</CODE>
          <SEVERITY>INFO</SEVERITY>
          <MESSAGE>The operation succeeded.</MESSAGE>
        </STATUS>
        <DTSERVER>20131110152328.855[-6:CST]</DTSERVER>
        <LANGUAGE>ENG</LANGUAGE>
        <FI>
          <ORG>EXAMPLE</ORG>
          <FID>12345</FID>
        </FI>
      </SONRS>
    </SIGNONMSGSRSV1>
    <PROFMSGSRSV1>
      <PROFTRNRS>
        <TRNUID>10001</TRNUID>
        <STATUS>
          <CODE>0</CODE>
          <SEVERITY>INFO</SEVERITY>
        </STATUS>
        <PROFRS>
          <MSGSETLIST>
            <SIGNONMSGSET>
              <SIGNONMSGSETV1>
                <MSGSETCORE>
                  <VER>1</VER>
                  <URL>https://www.example.com</URL>
                  <OFXSEC>NONE</OFXSEC>
                  <TRANSPSEC>Y</TRANSPSEC>
                  <SIGNONREALM>Standard</SIGNONREALM>
                  <LANGUAGE>ENG</LANGUAGE>
                  <SYNCMODE>LITE</SYNCMODE>
                  <RESPFILEER>Y</RESPFILEER>
                  <INTU.TIMEOUT>180</INTU.TIMEOUT>
                </MSGSETCORE>
              </SIGNONMSGSETV1>
            </SIGNONMSGSET>
            <SIGNUPMSGSET>
              <SIGNUPMSGSETV1>
                <MSGSETCORE>
                  <VER>1</VER>
                  <URL>https://www.example.com</URL>
                  <OFXSEC>NONE</OFXSEC>
                  <TRANSPSEC>Y</TRANSPSEC>
                  <SIGNONREALM>Standard</SIGNONREALM>
                  <LANGUAGE>ENG</LANGUAGE>
                  <SYNCMODE>LITE</SYNCMODE>
                  <RESPFILEER>Y</RESPFILEER>
                  <INTU.TIMEOUT>180</INTU.TIMEOUT>
                </MSGSETCORE>
                <WEBENROLL>
                  <URL>www.example.com</URL>
                </WEBENROLL>
                <CHGUSERINFO>N</CHGUSERINFO>
                <AVAILACCTS>Y</AVAILACCTS>
                <CLIENTACTREQ>N</CLIENTACTREQ>
              </SIGNUPMSGSETV1>
            </SIGNUPMSGSET>
            <BANKMSGSET>
              <BANKMSGSETV1>
                <MSGSETCORE>
                  <VER>1</VER>
                  <URL>https://www.example.com</URL>
                  <OFXSEC>NONE</OFXSEC>
                  <TRANSPSEC>Y</TRANSPSEC>
                  <SIGNONREALM>Standard</SIGNONREALM>
                  <LANGUAGE>ENG</LANGUAGE>
                  <SYNCMODE>FULL</SYNCMODE>
                  <RESPFILEER>Y</RESPFILEER>
                  <INTU.TIMEOUT>180</INTU.TIMEOUT>
                </MSGSETCORE>
                <CLOSINGAVAIL>Y</CLOSINGAVAIL>
                <XFERPROF>
                  <PROCDAYSOFF>SATURDAY</PROCDAYSOFF>
                  <PROCDAYSOFF>SUNDAY</PROCDAYSOFF>
                  <PROCENDTM>140000</PROCENDTM>
                  <CANSCHED>N</CANSCHED>
                  <CANRECUR>N</CANRECUR>
                  <CANMODXFERS>N</CANMODXFERS>
                  <CANMODMDLS>N</CANMODMDLS>
                  <MODELWND>2</MODELWND>
                  <DAYSWITH>2</DAYSWITH>
                  <DFLTDAYSTOPAY>1</DFLTDAYSTOPAY>
                </XFERPROF>
                <EMAILPROF>
                  <CANEMAIL>N</CANEMAIL>
                  <CANNOTIFY>N</CANNOTIFY>
                </EMAILPROF>
              </BANKMSGSETV1>
            </BANKMSGSET>
            <BILLPAYMSGSET>
              <BILLPAYMSGSETV1>
                <MSGSETCORE>
                  <VER>1</VER>
                  <URL>https://www.example.com</URL>
                  <OFXSEC>NONE</OFXSEC>
                  <TRANSPSEC>Y</TRANSPSEC>
                  <SIGNONREALM>Standard</SIGNONREALM>
                  <LANGUAGE>ENG</LANGUAGE>
                  <SYNCMODE>FULL</SYNCMODE>
                  <RESPFILEER>Y</RESPFILEER>
                  <INTU.TIMEOUT>180</INTU.TIMEOUT>
                </MSGSETCORE>
                <DAYSWITH>0</DAYSWITH>
                <DFLTDAYSTOPAY>0</DFLTDAYSTOPAY>
                <XFERDAYSWITH>0</XFERDAYSWITH>
                <XFERDFLTDAYSTOPAY>0</XFERDFLTDAYSTOPAY>
                <PROCDAYSOFF>SATURDAY</PROCDAYSOFF>
                <PROCDAYSOFF>SUNDAY</PROCDAYSOFF>
                <PROCENDTM>120000.000[-6:CST]</PROCENDTM>
                <MODELWND>0</MODELWND>
                <POSTPROCWND>0</POSTPROCWND>
                <STSVIAMODS>Y</STSVIAMODS>
                <PMTBYADDR>Y</PMTBYADDR>
                <PMTBYXFER>N</PMTBYXFER>
                <PMTBYPAYEEID>N</PMTBYPAYEEID>
                <CANADDPAYEE>Y</CANADDPAYEE>
                <HASEXTDPMT>N</HASEXTDPMT>
                <CANMODPMTS>Y</CANMODPMTS>
                <CANMODMDLS>N</CANMODMDLS>
                <DIFFFIRSTPMT>N</DIFFFIRSTPMT>
                <DIFFLASTPMT>N</DIFFLASTPMT>
              </BILLPAYMSGSETV1>
            </BILLPAYMSGSET>
            <PROFMSGSET>
              <PROFMSGSETV1>
                <MSGSETCORE>
                  <VER>1</VER>
                  <URL>https://www.example.com</URL>
                  <OFXSEC>NONE</OFXSEC>
                  <TRANSPSEC>Y</TRANSPSEC>
                  <SIGNONREALM>Standard</SIGNONREALM>
                  <LANGUAGE>ENG</LANGUAGE>
                  <SYNCMODE>LITE</SYNCMODE>
                  <RESPFILEER>Y</RESPFILEER>
                  <INTU.TIMEOUT>180</INTU.TIMEOUT>
                </MSGSETCORE>
              </PROFMSGSETV1>
            </PROFMSGSET>
          </MSGSETLIST>
          <SIGNONINFOLIST>
            <SIGNONINFO>
              <SIGNONREALM>Standard</SIGNONREALM>
              <MIN>4</MIN>
              <MAX>20</MAX>
              <CHARTYPE>ALPHAORNUMERIC</CHARTYPE>
              <CASESEN>N</CASESEN>
              <SPECIAL>Y</SPECIAL>
              <SPACES>Y</SPACES>
              <PINCH>N</PINCH>
              <CHGPINFIRST>N</CHGPINFIRST>
            </SIGNONINFO>
          </SIGNONINFOLIST>
          <DTPROFUP>20131110212329.590</DTPROFUP>
          <FINAME>Example</FINAME>
          <ADDR1>PO Box 1234</ADDR1>
          <ADDR2>Any, OK 74123</ADDR2>
          <CITY>ANY</CITY>
          <STATE>OK</STATE>
          <POSTALCODE>741231234</POSTALCODE>
          <COUNTRY>USA</COUNTRY>
          <CSPHONE>(555) 555-5555</CSPHONE>
          <TSPHONE>555-555-5555</TSPHONE>
          <URL>https://www.example.com/example</URL>
          <EMAIL>support@example.org</EMAIL>
          <INTU.BROKERID>example.com</INTU.BROKERID>
        </PROFRS>
      </PROFTRNRS>
    </PROFMSGSRSV1>
  </OFX>

  val ofx1pofx = """<OFX>
                   |    <SIGNONMSGSRSV1>
                   |      <SONRS>
                   |        <STATUS>
                   |          <CODE>0
                   |          <SEVERITY>INFO
                   |          <MESSAGE>The operation succeeded.
                   |        </STATUS>
                   |        <DTSERVER>20131110152328.855[-6:CST]
                   |        <LANGUAGE>ENG
                   |        <FI>
                   |          <ORG>EXAMPLE
                   |          <FID>12345
                   |        </FI>
                   |      </SONRS>
                   |    </SIGNONMSGSRSV1>
                   |    <PROFMSGSRSV1>
                   |      <PROFTRNRS>
                   |        <TRNUID>10001
                   |        <STATUS>
                   |          <CODE>0
                   |          <SEVERITY>INFO
                   |        </STATUS>
                   |        <PROFRS>
                   |          <MSGSETLIST>
                   |            <SIGNONMSGSET>
                   |              <SIGNONMSGSETV1>
                   |                <MSGSETCORE>
                   |                  <VER>1
                   |                  <URL>https://www.example.com
                   |                  <OFXSEC>NONE
                   |                  <TRANSPSEC>Y
                   |                  <SIGNONREALM>Standard
                   |                  <LANGUAGE>ENG
                   |                  <SYNCMODE>LITE
                   |                  <RESPFILEER>Y
                   |                  <INTU.TIMEOUT>180
                   |                </MSGSETCORE>
                   |              </SIGNONMSGSETV1>
                   |            </SIGNONMSGSET>
                   |            <SIGNUPMSGSET>
                   |              <SIGNUPMSGSETV1>
                   |                <MSGSETCORE>
                   |                  <VER>1
                   |                  <URL>https://www.example.com
                   |                  <OFXSEC>NONE
                   |                  <TRANSPSEC>Y
                   |                  <SIGNONREALM>Standard
                   |                  <LANGUAGE>ENG
                   |                  <SYNCMODE>LITE
                   |                  <RESPFILEER>Y
                   |                  <INTU.TIMEOUT>180
                   |                </MSGSETCORE>
                   |                <WEBENROLL>
                   |                  <URL>www.example.com
                   |                </WEBENROLL>
                   |                <CHGUSERINFO>N
                   |                <AVAILACCTS>Y
                   |                <CLIENTACTREQ>N
                   |              </SIGNUPMSGSETV1>
                   |            </SIGNUPMSGSET>
                   |            <BANKMSGSET>
                   |              <BANKMSGSETV1>
                   |                <MSGSETCORE>
                   |                  <VER>1
                   |                  <URL>https://www.example.com
                   |                  <OFXSEC>NONE
                   |                  <TRANSPSEC>Y
                   |                  <SIGNONREALM>Standard
                   |                  <LANGUAGE>ENG
                   |                  <SYNCMODE>FULL
                   |                  <RESPFILEER>Y
                   |                  <INTU.TIMEOUT>180
                   |                </MSGSETCORE>
                   |                <CLOSINGAVAIL>Y
                   |                <XFERPROF>
                   |                  <PROCDAYSOFF>SATURDAY
                   |                  <PROCDAYSOFF>SUNDAY
                   |                  <PROCENDTM>140000
                   |                  <CANSCHED>N
                   |                  <CANRECUR>N
                   |                  <CANMODXFERS>N
                   |                  <CANMODMDLS>N
                   |                  <MODELWND>2
                   |                  <DAYSWITH>2
                   |                  <DFLTDAYSTOPAY>1
                   |                </XFERPROF>
                   |                <EMAILPROF>
                   |                  <CANEMAIL>N
                   |                  <CANNOTIFY>N
                   |                </EMAILPROF>
                   |              </BANKMSGSETV1>
                   |            </BANKMSGSET>
                   |            <BILLPAYMSGSET>
                   |              <BILLPAYMSGSETV1>
                   |                <MSGSETCORE>
                   |                  <VER>1
                   |                  <URL>https://www.example.com
                   |                  <OFXSEC>NONE
                   |                  <TRANSPSEC>Y
                   |                  <SIGNONREALM>Standard
                   |                  <LANGUAGE>ENG
                   |                  <SYNCMODE>FULL
                   |                  <RESPFILEER>Y
                   |                  <INTU.TIMEOUT>180
                   |                </MSGSETCORE>
                   |                <DAYSWITH>0
                   |                <DFLTDAYSTOPAY>0
                   |                <XFERDAYSWITH>0
                   |                <XFERDFLTDAYSTOPAY>0
                   |                <PROCDAYSOFF>SATURDAY
                   |                <PROCDAYSOFF>SUNDAY
                   |                <PROCENDTM>120000.000[-6:CST]
                   |                <MODELWND>0
                   |                <POSTPROCWND>0
                   |                <STSVIAMODS>Y
                   |                <PMTBYADDR>Y
                   |                <PMTBYXFER>N
                   |                <PMTBYPAYEEID>N
                   |                <CANADDPAYEE>Y
                   |                <HASEXTDPMT>N
                   |                <CANMODPMTS>Y
                   |                <CANMODMDLS>N
                   |                <DIFFFIRSTPMT>N
                   |                <DIFFLASTPMT>N
                   |              </BILLPAYMSGSETV1>
                   |            </BILLPAYMSGSET>
                   |            <PROFMSGSET>
                   |              <PROFMSGSETV1>
                   |                <MSGSETCORE>
                   |                  <VER>1
                   |                  <URL>https://www.example.com
                   |                  <OFXSEC>NONE
                   |                  <TRANSPSEC>Y
                   |                  <SIGNONREALM>Standard
                   |                  <LANGUAGE>ENG
                   |                  <SYNCMODE>LITE
                   |                  <RESPFILEER>Y
                   |                  <INTU.TIMEOUT>180
                   |                </MSGSETCORE>
                   |              </PROFMSGSETV1>
                   |            </PROFMSGSET>
                   |          </MSGSETLIST>
                   |          <SIGNONINFOLIST>
                   |            <SIGNONINFO>
                   |              <SIGNONREALM>Standard
                   |              <MIN>4
                   |              <MAX>20
                   |              <CHARTYPE>ALPHAORNUMERIC
                   |              <CASESEN>N
                   |              <SPECIAL>Y
                   |              <SPACES>Y
                   |              <PINCH>N
                   |              <CHGPINFIRST>N
                   |            </SIGNONINFO>
                   |          </SIGNONINFOLIST>
                   |          <DTPROFUP>20131110212329.590
                   |          <FINAME>Example
                   |          <ADDR1>PO Box 1234
                   |          <ADDR2>Any, OK 74123
                   |          <CITY>ANY
                   |          <STATE>OK
                   |          <POSTALCODE>741231234
                   |          <COUNTRY>USA
                   |          <CSPHONE>(555) 555-5555
                   |          <TSPHONE>555-555-5555
                   |          <URL>https://www.example.com/example
                   |          <EMAIL>support@example.org
                   |          <INTU.BROKERID>example.com
                   |        </PROFRS>
                   |      </PROFTRNRS>
                   |    </PROFMSGSRSV1>
                   |  </OFX>""".stripMargin

  val accountList = """OFXHEADER:100
                      |DATA:OFXSGML
                      |VERSION:102
                      |SECURITY:NONE
                      |ENCODING:USASCII
                      |CHARSET:1252
                      |COMPRESSION:NONE
                      |OLDFILEUID:NONE
                      |NEWFILEUID:NONE
                      |
                      |<OFX><SIGNONMSGSRSV1><SONRS><STATUS><CODE>0<SEVERITY>INFO<MESSAGE>The operation succeeded.</STATUS><DTSERVER>20131110222957
                      |.563[-6:CST]<LANGUAGE>ENG<FI><ORG>EXAMPLE<FID>12345</FI></SONRS></SIGNONMSGSRSV1><SIGNUPMSGSRSV1><ACCTINFOTRNRS><TRNUID>11001<STATUS><CODE>0<SEVERITY>INFO</STATUS><ACCTINFORS
                      |><DTACCTUP>20131111042958.297<ACCTINFO><DESC>00001<BANKACCTINFO><BANKACCTFROM><BANKID>R1234<ACCTID>0001<ACCTTYPE>SAVINGS</BANKACCTFROM><SUPTXDL>Y<XFERSRC>Y<XFERDEST>Y
                      |<SVCSTATUS>ACTIVE</BANKACCTINFO></ACCTINFO><ACCTINFO><DESC>0002<BANKACCTINFO><BANKACCTFROM><BANKID>R1234<ACCTID>0002<ACCTTYPE>CHECKING</BANKACCTFROM><SUPTXDL>Y<XFERSRC>Y
                      |<XFERDEST>Y<SVCSTATUS>ACTIVE</BANKACCTINFO></ACCTINFO></ACCTINFORS></ACCTINFOTRNRS></SIGNUPMSGSRSV1></OFX>""".stripMargin
}
