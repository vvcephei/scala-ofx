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

  def fromDateTimeStringNoFracSec(dateTime: String): DateTime = dtfNoFracSec parseDateTime dateTime

  private val dtz = """(\d+\.\d+)(\[([^:]+):([^\]]+)\])?""".r("datetime", "zone", "offset", "tzname")
  private val dt = """(\d{14}\.\d{3})""".r("datetime")
  private val dtNoFracSec = """(\d{14})""".r("datetime")
  private val d = """(\d{8})""".r("date")

  def fromStringInferred(dateTime: String): DateTime = dateTime match {
    case dtz(datetime, _, null, _) => fromDateTimeString(datetime)
    case dtz(datetime, _, offset, _) => fromDateTimeString(datetime).withZone(DateTimeZone.forOffsetHours(offset.toInt))
    case dt(datetime) => fromDateTimeString(datetime)
    case dtNoFracSec(datetime) => fromDateTimeStringNoFracSec(datetime)
    case d(date) => fromDateString(date)
  }
}
