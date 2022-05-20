package com.example.webtours.feeders

import com.example.webtours.utils.data.{City, SeatType, SeatingPreference}

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.util.Date

object Feeders {

  val departCities = List("Denver", "Frankfurt", "London","Los Angeles", "Paris")
  val arriveCities = List("Portland", "San Francisco", "Seattle", "Sydney", "Zurich")

  var orders: Iterator[Map[String, Object]] = Iterator.continually(
    Map(
      "departDate" -> getDate(6),
      "arriveDate" -> getDate(10),
      "seatType" -> SeatType(scala.util.Random.nextInt(SeatType.maxId)),
      "seatPref" -> SeatingPreference(scala.util.Random.nextInt(SeatingPreference.maxId)),
      "arriveCity" -> scala.util.Random.shuffle(arriveCities).last,
      "departCity" -> scala.util.Random.shuffle(departCities).last,
    )
  )

  def getDate(plusDays: Int): String = {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dt = new Date()
    val date = LocalDate.parse(dt.toInstant.toString.replaceAll("T.*", "")).plusDays(plusDays)
    date.format(format)
  }

}
