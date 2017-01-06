package com.raj.config

import com.raj.model.TransactionLoggingRequest
import com.raj.remote.TransactionLoggingClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.concurrent.Future

@Service
class TestService {
    @Autowired
    private TransactionLoggingClient client;

    public void create(String resource) {
        TransactionLoggingRequest request = TransactionLoggingRequest.builder()
                .serviceId("serviceId")
                .transactionId("transactionId")
                .traceId("traceId")
                .operation("operation")
                .direction(TransactionLoggingRequest.Direction.Inbound)
                .body(resource)
                .build();

        Future<Boolean> result = client.log(request)
        result.get()
    }
}
