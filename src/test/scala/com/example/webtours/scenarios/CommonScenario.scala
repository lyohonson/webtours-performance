package com.example.webtours.scenarios

import com.example.webtours.cases.Webtours
import com.example.webtours.feeders.Feeders.orders
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{CookieKey, getCookieValue}

object CommonScenario {
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {

  val login = group("Login") {
    exec(Webtours.postLogin)
      .exec(getCookieValue(CookieKey("Set-Cookie")))
      .exec(Webtours.getNavHome)
      .exec(Webtours.getLogin)
  }

  val openFlights = group("Open flights") {
    exec(Webtours.getWelcome)
      .exec(Webtours.getNavFlights)
      .exec(Webtours.getReservations)
  }

  val setFlightPreferences = group("Set flight preferences") {
    exec(Webtours.postReservationsDirection)
      .exec(Webtours.postReservationsSeat)
      .exec(Webtours.postReservationsPaymentDetails)
  }

  val openHomePage = group("Open home page") {
    exec(Webtours.getWelcome)
      .exec(Webtours.getNavHome)
      .exec(Webtours.getLogin)
  }

  val scn: ScenarioBuilder = scenario("Common Scenario")
    .feed(orders)
    .exec(Webtours.openMainPage)
    .exec(login)
    .exec(openFlights)
    .exec(setFlightPreferences)
    .exec(openHomePage)


}

