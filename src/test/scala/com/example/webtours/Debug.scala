package com.example.webtours

import com.example.webtours.scenarios.CommonScenario
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._

class Debug extends Simulation {

  // proxy is required on localhost:8888

  setUp(
    CommonScenario().inject(atOnceUsers(2))
  ).protocols(httpProtocol)
    .maxDuration(testDuration)

}
