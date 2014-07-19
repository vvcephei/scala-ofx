package org.vvcephei.scalaofx.lib.model.response

import org.joda.time.DateTime
import org.vvcephei.scalaofx.lib.model.Account


case class CreditCardStatement(currency: String, account: Account, startTime: DateTime, endTime: Option[DateTime], transactions: Seq[Transaction], ledgerBalance: Double, availableBalance: Option[Double])




