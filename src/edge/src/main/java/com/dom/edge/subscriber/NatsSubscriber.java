package com.dom.edge.subscriber;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.dispatcher.ProtoDispatcher;
import io.nats.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.CountDownLatch;

public abstract class NatsSubscriber<T, S> {
    String subject;
    protected static final Logger logger = LoggerFactory.getLogger(NatsSubscriber.class);

    protected final JpaRepository<S, Long> repository;

    CountDownLatch latch;

    Dispatcher dispatcher;

    protected NatsSubscriber(String s, ProtoDispatcher d, JpaRepository<S, Long> repository) {
        subject = s;
        this.repository = repository;
        dispatcher = d.getDispatcher();
        start();
    }

    protected void start() {
        logger.info("Subscribing to {}", subject);
        dispatcher.subscribe(subject, (msg) -> {
            T event = parseProto(msg.getData());
            S event_model = convertToModel(event);
            logger.info("Message from {}: Starting persist");
            repository.save(event_model);
            logger.info("Persisted data");
        });
    }

    public abstract T parseProto(byte[] data);

    public abstract S convertToModel(T proto);

}
