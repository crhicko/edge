package com.dom.edge.subscriber;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.dispatcher.ProtoDispatcher;
import com.dom.edge.model.SportEvent;
import com.dom.edge.proto.Sportevent;
import com.dom.edge.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportsNatsSubscriber extends NatsSubscriber<Sportevent.event, SportEvent> {

    @Autowired
    SportsNatsSubscriber(ProtoDispatcher d, SportRepository sportRepository){
        super("sport_event", d,  sportRepository);
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
        SportEvent s = new SportEvent(proto.getSport().getDescriptorForType().getFullName(), proto.getMatchTitle(), proto.getDataEvent());
        proto.getAllFields().forEach((k,v) -> {
            logger.info("{} : {}",k,v);
        });
        return s;
    }
}
