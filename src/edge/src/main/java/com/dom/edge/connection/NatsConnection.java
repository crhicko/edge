package com.dom.edge.connection;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NatsConnection {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(NatsConnection.class);

    public NatsConnection() {
        try {
            logger.info("trying to connect");
            connection = Nats.connect("host.docker.internal:4222");
            logger.info("connected");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Unable to connect to NATS server. Exiting.");
            System.exit(-1);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
