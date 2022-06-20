package com.dom.edge.dispatcher;

import com.dom.edge.connection.NatsConnection;
import io.nats.client.Dispatcher;
import org.springframework.stereotype.Component;

@Component
public class ProtoDispatcher {
    private Dispatcher dispatcher;

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    ProtoDispatcher(NatsConnection nc) {
        dispatcher = nc.getConnection().createDispatcher(msg -> {});
    }
}
