package org.vvcephei.scalaofx.client

import org.vvcephei.scalaofx.lib.message._
import com.sun.jersey.api.client.Client
import org.vvcephei.scalaofx.lib.parser.{OfxParser, TopLevelOfxMessageParser, OfxMessage}
import org.vvcephei.scalaofx.lib.model.response.{BankStatementResponse, BankStatement, BankAccountInfo}
import scala.xml.PrettyPrinter
import org.vvcephei.scalaofx.lib.model.Account
import org.vvcephei.scalaofx.lib.model.User
import org.vvcephei.scalaofx.lib.message.SignOn
import org.vvcephei.scalaofx.lib.model.Bank
import org.vvcephei.scalaofx.lib.message.BankStatementRequest
import org.joda.time.DateTime

case class BankClient(user: User, bank: Bank, client: Client = new Client(), debug: Boolean = false) {
  lazy val ppr = new PrettyPrinter(80, 2)

  private val signOnRequest = Header.header + "\n" +
    OfxParser.toStrictOfx(
      <OFX>
        {SignOn(user, bank).toOfx}
      </OFX>
    )

  /**
   * Sign on to bank as user.
   * @return The raw sign on response
   */
  def signOn(): OfxMessage = {
    val contentType = "application/x-ofx"
    if (debug) println(bank.bootstrapUrl)
    if (debug) println(contentType)
    if (debug) println(signOnRequest)
    val response = client.resource(bank.bootstrapUrl).`type`(contentType).post(classOf[String], signOnRequest)
    val parsed = TopLevelOfxMessageParser.parse(response)
    parsed
  }

  private val profileRequest = Header.header + "\n" +
    OfxParser.toStrictOfx(
      <OFX>
        {SignOn(user, bank).toOfx}<PROFMSGSRQV1>
        {Profile.toOfx}
      </PROFMSGSRQV1>
      </OFX>
    )

  /**
   * @return For now, just the parsed result of the query in ofx format
   */
  def profile(): OfxMessage = {
    if (debug) println(profileRequest)
    val response = client.resource(bank.bootstrapUrl).`type`("application/x-ofx").post(classOf[String], profileRequest)
    val parsed = TopLevelOfxMessageParser.parse(response)
    parsed
  }

  private val bankAccountInfoRequest = Header.header + "\n" +
    OfxParser.toStrictOfx(
      <OFX>
        {SignOn(user, bank).toOfx}<SIGNUPMSGSRQV1>
        {AccountInfo.toOfx}
      </SIGNUPMSGSRQV1>
      </OFX>
    )

  def accountInfo(): Seq[BankAccountInfo] = {
    if (debug) println(bankAccountInfoRequest)
    val response = client.resource(bank.bootstrapUrl).`type`("application/x-ofx").post(classOf[String], bankAccountInfoRequest)
    val parsed = TopLevelOfxMessageParser.parse(response)
    if (debug) println(ppr format parsed.ofx)
    BankAccountInfo.fromOfx(parsed.ofx)
  }

  private def bankStatementRequest(accounts: Seq[Account], startDate: DateTime) = Header.header + "\n" +
    OfxParser.toStrictOfx(
      <OFX>
        {SignOn(user, bank).toOfx}<BANKMSGSRQV1>
        {for (account <- accounts) yield {
          BankStatementRequest(account, startDate).toOfx
        }}
      </BANKMSGSRQV1>
      </OFX>
    )

  def bankStatements(accounts: Seq[Account], startDate: DateTime): BankStatementResponse = {
    val request: String = bankStatementRequest(accounts, startDate)
    if (debug) println(request)
    val response = client.resource(bank.bootstrapUrl).`type`("application/x-ofx").post(classOf[String], request)
    val parsed = TopLevelOfxMessageParser parse response
    if (debug) println(ppr format parsed.ofx)
    BankStatement.fromOfx(parsed.ofx)
  }
}
