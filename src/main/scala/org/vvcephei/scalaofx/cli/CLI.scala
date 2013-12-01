package org.vvcephei.scalaofx.cli

import com.fasterxml.jackson.databind.{SerializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.datatype.joda.JodaModule
import scala.xml.PrettyPrinter
import com.beust.jcommander.JCommander
import org.vvcephei.scalaofx.client.BankClient
import org.vvcephei.scalaofx.lib.parser.OfxMessage
import org.vvcephei.scalaofx.lib.model.response.{BankStatementResponse, BankAccountInfo}

object CLI {
  private val mapper = new ObjectMapper() with ScalaObjectMapper {
    registerModule(DefaultScalaModule)
    registerModule(new JodaModule)
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
  } writerWithDefaultPrettyPrinter()
  private val pr = new PrettyPrinter(80, 2)

  def main(args: Array[String]): Unit = {
    new JCommander(Options, args: _*)

    for ((login, accounts) <- Options.loginToAccounts) {
      val bc = BankClient(login.user, login.bank, debug = Options.verbose)

      println()
      println("========================================")
      println(accounts)
      println(login)

      println()
      println(" sign on")
      val signon = bc.signOn()
      println(pr format signon.ofx)

      println()
      println(" profile")
      val profile: OfxMessage = bc.profile()
      println(pr format profile.ofx)

      println()
      println(" acctinfo")
      val info: Seq[BankAccountInfo] = bc.accountInfo()
      println(mapper writeValueAsString info)

      println()
      println(" statement")
      val statements: Seq[BankStatementResponse] = bc.bankStatements(accounts, Options.start)
      println(mapper writeValueAsString statements)

      for (statement <- statements) {
        println(s"Balance: ${statement.availableBalance}")
        for (transaction <- statement.transactions) {
          println(s"date: ${transaction.posted}, type: ${transaction.`type`}, amount: ${transaction.amount}, payee: ${transaction.name}, memo: ${transaction.memo}")
        }
      }
    }
  }
}
