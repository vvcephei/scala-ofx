package org.vvcephei.scalaofx.lib.message

import org.joda.time.{DateTimeZone, DateTime}
import org.joda.time.format.DateTimeFormat
import java.util.UUID

object Util {
  def trnuid() = UUID.randomUUID().toString.toUpperCase

  private lazy val df = DateTimeFormat.forPattern("YYYYMMdd")

  def toDateString(date: DateTime): String = df print date

  def fromDateString(date: String): DateTime = df parseDateTime date

  private lazy val dtf = DateTimeFormat.forPattern("YYYYMMddHHmmss.SSS")

  private lazy val dtfNoFracSec = DateTimeFormat.forPattern("YYYYMMddHHmmss")

  def toDateTimeString(dateTime: DateTime): String = dtf print dateTime

  def fromDateTimeString(dateTime: String): DateTime = dtf parseDateTime dateTime

  def fromDateTimeZoneString(dateTime: String, zone: DateTimeZone): DateTime = dtf.withZone(zone) parseDateTime dateTime

  def fromDateTimeStringNoFracSec(dateTime: String): DateTime = dtfNoFracSec parseDateTime dateTime

  def fromDateTimeZoneStringNoFracSec(dateTime: String, zone: DateTimeZone): DateTime = dtfNoFracSec.withZone(zone) parseDateTime dateTime

  private val dt = """(\d{8})(\d{6})?(\.\d{3})?(\[([^:]+)?:([^\]]+)?\])?""".r("date", "time?", "milli?", "zonegroup?", "zone:offset?", "zone:name?")

  def fromStringInferred(dateTime: String): DateTime = dateTime match {
    case dt(date, null, null, _, null, _) => fromDateString(date)
    case dt(date, time, null, _, null, _) => fromDateTimeStringNoFracSec(date + time)
    case dt(date, time, milli, _, null, _) => fromDateTimeString(date + time + milli)
    case dt(date, time, null, _, offset, _) => fromDateTimeZoneStringNoFracSec(date + time, DateTimeZone.forOffsetHours(offset.toInt))
    case dt(date, time, milli, _, offset, _) => fromDateTimeZoneString(date + time + milli, DateTimeZone.forOffsetHours(offset.toInt))
  }
}
