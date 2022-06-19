package com.dom.edge;

import com.dom.edge.proto.Executionevent;
import io.nats.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public abstract class NatsSubscriber<T> {
    String subject;
    private static final Logger logger = LoggerFactory.getLogger(NatsSubscriber.class);

    CountDownLatch latch;

    Dispatcher dispatcher;

    protected NatsSubscriber(String s, Connection nc, CountDownLatch l) {
        subject = s;
        latch = l;
        dispatcher = createProtoDispatcher(nc);
        start();
    }

    protected Dispatcher createProtoDispatcher(Connection nc) {
        return nc.createDispatcher((msg) -> {
//            T event = T.parseFrom(msg.getData());
            T event = parseProto(msg.getData());
            logger.info("Message from {}: {}", subject, event.getClass().getSimpleName());
            latch.countDown();
        });
    }

    protected void start() {
        logger.info("Subscribing to {}", subject);
        dispatcher.subscribe(subject);
    }

    public abstract T parseProto(byte[] data);

//    public void listen() {
//        latch.await();
//    }

}
