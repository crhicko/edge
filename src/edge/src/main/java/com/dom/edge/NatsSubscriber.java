package com.dom.edge;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.proto.Executionevent;
import io.nats.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public abstract class NatsSubscriber<T, S> {
    String subject;
    protected static final Logger logger = LoggerFactory.getLogger(NatsSubscriber.class);

    protected final JpaRepository<S, Long> repository;

    CountDownLatch latch;

    Dispatcher dispatcher;

    protected NatsSubscriber(String s, NatsConnection nc, JpaRepository<S, Long> repository) {
        logger.info("Constructing");
        subject = s;
        latch = new CountDownLatch(1);
        this.repository = repository;
        dispatcher = createProtoDispatcher(nc.getConnection(), repository);
        start();
        listen();
    }

    protected Dispatcher createProtoDispatcher(Connection nc, JpaRepository repository) {

        return nc.createDispatcher((msg) -> {
//            T event = T.parseFrom(msg.getData());
            T event = parseProto(msg.getData());
            S event_model = convertToModel(event);
            logger.info("Message from {}: {} {}", subject, event.getClass().getSimpleName(), event_model.getClass().getSimpleName(), repository.getClass().getSimpleName());
            logger.info("Repository is: {}", repository.getClass().getInterfaces()[0].getSimpleName());
            latch.countDown();
            repository.save(event_model);
            logger.info("Persisted data");

        });
    }

    protected void start() {
        logger.info("Subscribing to {}", subject);
        dispatcher.subscribe(subject);
    }

    public abstract T parseProto(byte[] data);

    public abstract S convertToModel(T proto);

    public void listen() {
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
