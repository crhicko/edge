package com.dom.edge;

import com.dom.edge.proto.Sportevent;
import io.nats.client.Connection;

import java.util.concurrent.CountDownLatch;

public class SportsNatsSubscriber extends NatsSubscriber<Sportevent.event> {

    SportsNatsSubscriber(Connection nc, CountDownLatch l){
        super("sport_event", nc, l);
    }
    public Sportevent.event parseProto(byte[] data) {
        try {
            return Sportevent.event.parseFrom(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
