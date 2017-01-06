package com.raj.config

import com.raj.remote.TransactionLoggingClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.concurrent.Future

@Service
class TestService {
    @Autowired
    private TransactionLoggingClient client;

    public void create(String resource) {
        Future<Boolean> result = client.log(null)
        result.get()
    }
}
