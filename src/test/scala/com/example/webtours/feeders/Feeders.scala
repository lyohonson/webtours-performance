package com.example.webtours.feeders

import com.example.webtours.data.{SeatType, SeatingPreference}
import io.gatling.core.Predef._
import io.gatling.core.feeder.{BatchableFeederBuilder, Feeder}
import ru.tinkoff.gatling.feeders.{RandomDateRangeFeeder, RandomStringFeeder}

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

object Feeders {

  val flightDates: Feeder[String] =
    RandomDateRangeFeeder("departDate", "arriveDate", 10, "yyyy/MM/dd", LocalDateTime.now(), ChronoUnit.DAYS)

  val sessionIds: Feeder[String] = RandomStringFeeder("sessionStringId", 37)

  var orderPrefs: Iterator[Map[String, Object]] = Iterator.continually(
    Map(
      "seatType" -> SeatType(scala.util.Random.nextInt(SeatType.maxId)),
      "seatPref" -> SeatingPreference(scala.util.Random.nextInt(SeatingPreference.maxId)),
      "flightNumber" -> scala.util.Random.nextInt(3).toString
    )
  )

  val arriveCities: BatchableFeederBuilder[String] = csv("arriveCities.csv").circular
  val departCities: BatchableFeederBuilder[String] = csv("departCities.csv").circular

}
