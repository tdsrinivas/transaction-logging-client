package com.raj

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import static com.github.tomakehurst.wiremock.client.WireMock.*

class TestControllerTest extends BaseTransactionLoggingTest {
    @Autowired
    private TestRestTemplate restTemplate;

    def "test controller"() {
        setup:
        loggingService.stubFor(post(urlEqualTo("/alpha/transactions"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)))

        when:
        restTemplate.postForEntity("/resources", "resource string", void.class);

        then:
        loggingService.verify(postRequestedFor(urlEqualTo("/alpha/transactions")));
    }
}
