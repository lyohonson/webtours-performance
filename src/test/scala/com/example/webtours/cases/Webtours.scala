package com.example.webtours.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

object Webtours {

  val openMainPage: HttpRequestBuilder = http("GET /webtours")
    .get("/webtours")
    .check(status is 200)

  val openFlights: HttpRequestBuilder = http("GET /")
    .get("/webtours")
    .check(status is 200)

  val postLogin: HttpRequestBuilder = http("POST /login.pl")
    .post("/cgi-bin/login.pl")
    .formParamSeq(Seq(("username", "ashugaev1"),
      ("password", "Qwerty123"),
      ("userSession", "133937.#{sessionStringId}"),
      ("JSFormSubmit", "off")))
    .check(status is 200)

  val getNavHome: HttpRequestBuilder = http("GET /nav.pl")
    .get("/cgi-bin/nav.pl")
    .queryParamSeq(Seq(("page", "menu"), ("in", "home")))
    .check(status is 200)

  val getLogin: HttpRequestBuilder = http("GET /login.pl")
    .get("/cgi-bin/login.pl")
    .queryParam("intro", "true")
    .check(status is 200)

  val getWelcome: HttpRequestBuilder = http("GET /welcome.pl")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
    .check(status is 200)

  val getNavFlights: HttpRequestBuilder = http("GET /nav.pl")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "flights")
    .check(status is 200)

  val getReservations: HttpRequestBuilder = http("GET /reservations.pl-welcome")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page", "welcome")
    .check(status is 200)

  val postReservationsDirection: HttpRequestBuilder = http("POST /reservations.pl-direction")
    .get("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", 0)
    .formParam("depart", "#{departCity}")
    .formParam("departDate", "#{departDate}")
    .formParam("arrive", "#{arriveCity}")
    .formParam("returnDate", "#{arriveDate}")
    .formParam("numPassengers", 1)
    .formParam("seatPref", "#{seatPref}")
    .formParam("seatType", "#{seatType}")
    .check(status is 200)

  val postReservationsSeat: HttpRequestBuilder = http("POST /reservations.pl-seat")
    .get("/cgi-bin/reservations.pl")
    .formParamSeq(Seq(("outboundFlight", "#{departNumber}#{arriveNumber}#{flightNumber};529;#{departDate}"),
      ("numPassengers", 1),
      ("advanceDiscount", 0),
      ("seatType", "#{seatType}"),
      ("seatPref", "#{seatPref}")
    ))
    .check(status is 200)

  val postReservationsPaymentDetails: HttpRequestBuilder = http("POST /reservations.pl-address")
    .get("/cgi-bin/reservations.pl")
    .formParamSeq(Seq(("firstName", "Aleksei"),
      ("lastName", "Ivanov"),
      ("address1", "address1"),
      ("address2", "address2"),
      ("pass1", "Window"),
      ("creditCard", 57),
      ("expDate", 0),
      ("numPassengers", 1),
      ("seatType", "#{seatType}"),
      ("seatPref", "#{seatPref}"),
      ("outboundFlight", "#{departNumber}#{arriveNumber}#{flightNumber};529;#{departDate}"),
      ("advanceDiscount", 0),
      ("JSFormSubmit", "off"),
    ))
    .check(status is 200)

  def getDate(plusDays: Int): String = {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dt = new Date()
    val date = LocalDate.parse(dt.toInstant.toString.replaceAll("T.*", "")).plusDays(plusDays)
    date.format(format)
  }
}
