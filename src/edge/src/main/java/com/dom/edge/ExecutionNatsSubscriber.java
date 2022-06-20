package com.dom.edge;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.model.ExecutionEvent;
import com.dom.edge.model.SportEvent;
import com.dom.edge.proto.Executionevent;
import com.dom.edge.proto.Sportevent;
import com.dom.edge.repository.ExecutionRepository;
import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
public class ExecutionNatsSubscriber extends NatsSubscriber<Executionevent.execution, ExecutionEvent>{

//    @Autowired
//    protected static ExecutionRepository executionRepository;


    @Autowired
    ExecutionNatsSubscriber(NatsConnection nc, ExecutionRepository executionRepository){
        super("execution", nc,  executionRepository);
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

    @Override
    public ExecutionEvent convertToModel(Executionevent.execution proto) {
        Map map = proto.getAllFields();
        ExecutionEvent e = new ExecutionEvent(proto.getSymbol(), proto.getMarket(), proto.getPrice(), proto.getQuantity(), proto.getExecutionEpoch(), proto.getStateSymbol());
        map.forEach((k,v) -> {
            logger.info("{} : {}",k,v);
        });
        return e;
    }
}
