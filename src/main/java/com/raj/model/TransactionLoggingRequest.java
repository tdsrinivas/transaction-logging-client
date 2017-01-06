package com.raj.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionLoggingRequest {
    public enum Direction {Inbound, Outbond};

    private final String serviceId;
    private final String transactionId;
    private final String traceId;
    private final String operation;
    private final Direction direction;
    private final Long timestamp = new Date().getTime();
    private final String body;
}
