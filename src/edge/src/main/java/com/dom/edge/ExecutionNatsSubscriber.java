package com.dom.edge;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.model.ExecutionEvent;
import com.dom.edge.proto.Executionevent;
import com.dom.edge.repository.ExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecutionNatsSubscriber extends NatsSubscriber<Executionevent.execution, ExecutionEvent>{

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
        ExecutionEvent e = new ExecutionEvent(proto.getSymbol(), proto.getMarket(), proto.getPrice(), proto.getQuantity(), proto.getExecutionEpoch(), proto.getStateSymbol());
        proto.getAllFields().forEach((k,v) -> {
            logger.debug("{} : {}",k,v);
        });
        return e;
    }
}
