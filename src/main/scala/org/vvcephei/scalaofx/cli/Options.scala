package org.vvcephei.scalaofx.cli

import com.beust.jcommander.Parameter
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime
import org.vvcephei.scalaofx.lib.model.{AccountType, Account, User, Bank}
import com.fasterxml.jackson.databind.{SerializationFeature, ObjectMapper}
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.datatype.joda.JodaModule
import java.io.File
import scala.collection.JavaConversions._
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

object Options {
  @Parameter(names = Array("-v", "--verbose"), description = "Prints lots of info about transactions")
  var verbose: Boolean = false

  @Parameter(names = Array("--ofx"), description = "Loads from an ofx file")
  var ofxFile: String = ""

  @Parameter(names = Array("-b", "--bank"), description = "bankName:user:pass")
  var banks: java.util.List[String] = Nil

  @Parameter(names = Array("-a", "--account"), description = "bankName:routing:accountNumber:accountType")
  var accounts: java.util.List[String] = Nil

  private val pattern = DateTimeFormat.forPattern("YYYY-MM-dd")
  @Parameter(names = Array("-s", "--start-date"), description = "YYYY-MM-dd start date for transactions (default = now - 30 days)")
  var startDate: String = pattern.print(DateTime.now minusDays 30)
  lazy val start = pattern.parseDateTime(startDate)

  @JsonIgnoreProperties(ignoreUnknown = true)
  case class Config(ofx: Map[String, Bank])

  @Parameter(names = Array("-c", "--config"), description = "config file")
  var configArg: String = null


  case class Login(user: User, bank: Bank)

  lazy val logins = (for (bank <- banks) yield {
    bank.split(':').toList match {
      case b :: u :: p :: Nil => b -> Login(User(u, p), config.ofx(b))
      case _ => throw new IllegalArgumentException
    }
  }).toMap

  private def toMultiMap[A, B](ps: Seq[(A, B)]): Map[A, Seq[B]] = ps groupBy { _._1 } mapValues { _.map(_._2) }

  lazy val loginToAccounts: Map[Login, Seq[Account]] = toMultiMap(for (account <- accounts) yield {
    account.split(':').toList match {
      case b :: r :: a :: t :: Nil => logins(b) -> Account(Some(r), Some(a), Some(AccountType.from(t)))
      case _ => throw new IllegalArgumentException
    }
  })


  lazy val yamlMapper = new ObjectMapper(new YAMLFactory) with ScalaObjectMapper {
    registerModule(DefaultScalaModule)
    registerModule(new JodaModule)
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
  }

  lazy val config = yamlMapper.readValue[Config](new File(configArg))

}