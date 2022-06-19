package com.dom.edge;

import com.dom.edge.proto.Executionevent;
import io.nats.client.Connection;

import java.util.concurrent.CountDownLatch;

public class ExecutionNatsSubscriber extends NatsSubscriber<Executionevent.execution>{

    ExecutionNatsSubscriber(Connection nc, CountDownLatch l){
        super("execution", nc, l);
    }

    @Override
    public Executionevent.execution parseProto(byte[] data) {
        try {
            return Executionevent.execution.parseFrom(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
