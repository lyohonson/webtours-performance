package com.example.webtours

import com.example.webtours.scenarios.CommonScenario
import io.gatling.core.Predef._
import ru.tinkoff.gatling.config.SimulationConfig._
import ru.tinkoff.gatling.influxdb.Annotations

class MaxPerformanceClose extends Simulation with Annotations {

  setUp(
    CommonScenario().inject(
      incrementConcurrentUsers((intensity / stagesNumber).toInt) // интенсивность на ступень
        .times(stagesNumber) // Количество ступеней
        .eachLevelLasting(stageDuration) // Длительность полки
        .separatedByRampsLasting(rampDuration) // Длительность разгона
        .startingFrom(0) // Начало нагрузки с
    )
  ).protocols(httpProtocol)
    .maxDuration(testDuration) // общая длительность теста
    .assertions(
      global.responseTime.percentile3.lt(5000),
      global.successfulRequests.percent.gt(95)
    )


}
