package com.dom.edge.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class ExecutionEvent {

    private static Logger logger = LoggerFactory.getLogger(ExecutionEvent.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exec_seq")
    @SequenceGenerator(name = "exec_seq")
    private Long id;

    private Timestamp timestamp;
    private String symbol;

    private String market;

    private Float price;

    private Float quantity;

    private Long execution_epoch;

    private String state_symbol;

    public ExecutionEvent(String symbol, String market, Float price, Float quantity, Long execution_epoch, String state_symbol) {
        logger.info("Creating execution event");
        this.symbol = symbol;
        this.market = market;
        this.price = price;
        this.quantity = quantity;
        this.execution_epoch = execution_epoch;
        this.state_symbol = state_symbol;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
