package com.raj.remote;

import com.raj.model.TransactionLoggingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

import static org.springframework.http.HttpMethod.POST;


@Component
public class TransactionLoggingClient {

    @Qualifier("loadBalancedRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Async
    public Future<Boolean> log(TransactionLoggingRequest request) {
        try {
            String url = "http://transaction-logging/alpha/transactions";
            HttpEntity<TransactionLoggingRequest> entity = new HttpEntity<>(request);

            restTemplate.exchange(url, POST, entity, void.class);

            return new AsyncResult(Boolean.TRUE);
        } catch (HttpServerErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                throw new RuntimeException("Service unavailable", ex);
            }
            throw ex;
        } catch (ResourceAccessException ex) {
            throw new RuntimeException("Server exception", ex);
        }
    }
}
