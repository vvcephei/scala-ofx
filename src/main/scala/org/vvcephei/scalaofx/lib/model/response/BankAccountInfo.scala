package org.vvcephei.scalaofx.lib.model.response

import org.vvcephei.scalaofx.lib.model.AccountType
import scala.xml.Elem

case class BankAccountInfo(description: String, routing: String, accountId: String, accountType: AccountType)

object BankAccountInfo {
  def fromOfx(ofx: Elem): Seq[BankAccountInfo] =
    for {
      acctInfo <- ofx \\ "ACCTINFO"
      desc <- acctInfo \ "DESC"
      bankAcctFrom <- acctInfo \ "BANKACCTINFO" \ "BANKACCTFROM"
      bankId <- bankAcctFrom \ "BANKID"
      acctId <- bankAcctFrom \ "ACCTID"
      acctType <- bankAcctFrom \ "ACCTTYPE"
    } yield {
      BankAccountInfo(desc.text, bankId.text, acctId.text, AccountType.valueOf(acctType.text))
    }
}
