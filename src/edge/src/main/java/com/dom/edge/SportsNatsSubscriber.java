package com.dom.edge;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.model.SportEvent;
import com.dom.edge.proto.Sportevent;
import com.dom.edge.repository.SportRepository;
import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
public class SportsNatsSubscriber extends NatsSubscriber<Sportevent.event, SportEvent> {

//    @Autowired
//    protected static SportRepository sportRepository;



    @Autowired
    SportsNatsSubscriber(NatsConnection nc, SportRepository sportRepository){
        super("sport_event", nc,  sportRepository);
        logger.info("{}", repository.getClass().getSimpleName());
    }
    @Override
    public Sportevent.event parseProto(byte[] data) {
        try {
            return Sportevent.event.parseFrom(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SportEvent convertToModel(Sportevent.event proto) {
        Map map = proto.getAllFields();
        SportEvent s = new SportEvent(proto.getSport().getDescriptorForType().getFullName(), proto.getMatchTitle(), proto.getDataEvent());
        map.forEach((k,v) -> {
            logger.info("{} : {}",k,v);
        });
        return s;
    }
}
