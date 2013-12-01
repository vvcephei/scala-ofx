package org.vvcephei.scalaofx.lib.message

import org.joda.time.{DateTimeZone, DateTime}
import org.joda.time.format.DateTimeFormat
import java.util.UUID

object Util {
  def trnuid() = UUID.randomUUID().toString.toUpperCase

  private lazy val df = DateTimeFormat.forPattern("YYYYMMdd")

  def toDateString(date: DateTime): String = df print date

  private lazy val dtf = DateTimeFormat.forPattern("YYYYMMddHHmmss.SSS")

  def toDateTimeString(dateTime: DateTime): String = dtf print dateTime

  def fromDateTimeString(dateTime: String): DateTime = dtf parseDateTime dateTime

  private val dtz = """(\d+\.\d+)(\[([^:]+):([^\]]+)\])?""".r("datetime", "zone", "offset", "tzname")

  def fromDateTimeWithZoneString(dateTime: String): DateTime = dateTime match {
    case dtz(datetime, _, null, _) => fromDateTimeString(datetime)
    case dtz(datetime, _, offset, _) => fromDateTimeString(datetime).withZone(DateTimeZone.forOffsetHours(offset.toInt))
  }
}
