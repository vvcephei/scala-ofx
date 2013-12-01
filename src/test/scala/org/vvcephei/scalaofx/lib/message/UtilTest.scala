package org.vvcephei.scalaofx.lib.message

import org.scalatest.FunSuite
import org.joda.time.{DateTimeZone, DateTime}

class UtilTest extends FunSuite {
  test("Util.toDateString should format a DateTime") {
    assert(Util.toDateString(new DateTime(2013,9,2,0,0)) == "20130902")
  }

  test("Util.fromDateTimeWithZoneString should parse a full ofx date") {
    assert(Util.fromDateTimeWithZoneString("20130813070000.000[-5:CDT]").toString() == new DateTime(2013,8,13,7,0,DateTimeZone.forID("CST6CDT")).toString())
  }

  test("Util.fromDateTimeWithZoneString should parse a partial ofx date") {
    assert(Util.fromDateTimeWithZoneString("20130813070000.000").toString() == new DateTime(2013,8,13,7,0).toString())
  }
}
