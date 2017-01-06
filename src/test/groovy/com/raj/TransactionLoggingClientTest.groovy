package com.raj

import com.fasterxml.jackson.databind.ObjectMapper
import com.raj.remote.TransactionLoggingClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

import static com.github.tomakehurst.wiremock.client.WireMock.*

class TransactionLoggingClientTest extends BaseTransactionLoggingTest {
    @Autowired
    private ObjectMapper mapper

    @Autowired
    private TransactionLoggingClient client;

    def "test logging client"() {
        setup:
        loggingService.stubFor(post(urlEqualTo("/alpha/transactions"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)))

        when:
        def result = client.log(null);
        result.get()

        then:
        loggingService.verify(postRequestedFor(urlEqualTo("/alpha/transactions")));
    }
}
