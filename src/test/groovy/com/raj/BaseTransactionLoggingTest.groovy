package com.raj

import com.github.tomakehurst.wiremock.WireMockServer
import com.raj.config.TestApplication
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class BaseTransactionLoggingTest extends Specification {
    protected static WireMockServer loggingService = new WireMockServer(9999);

    def setup() {
        loggingService.start()
        loggingService.resetMappings();
        loggingService.resetRequests();
    }

    def cleanupSpec() {
        loggingService.stop();
    }
}
