package com.dom.edge;

import io.nats.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class NatsSubscriber {
    String subject;
    Logger logger = LoggerFactory.getLogger(NatsSubscriber.class);

    CountDownLatch latch;

    Dispatcher dispatcher;

    public NatsSubscriber(String s, Connection nc, CountDownLatch l) {
        subject = s;
        latch = l;
        dispatcher = nc.createDispatcher((msg) -> {
            String resp = new String(msg.getData(), StandardCharsets.UTF_8);
            logger.info("Message from {}: {}", subject, resp);
            latch.countDown();
        });
        logger.info("Subscribing to {}", subject);
        dispatcher.subscribe(s);
    }

}
