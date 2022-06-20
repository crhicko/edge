package com.dom.edge;

import com.dom.edge.connection.NatsConnection;
import com.dom.edge.repository.SportRepository;
import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dom.edge.NatsSubscriber;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class EdgeApplication {

	private static final Logger logger = LoggerFactory.getLogger(EdgeApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(EdgeApplication.class, args);

		NatsConnection nc = ctx.getBean(NatsConnection.class);



//		NatsSubscriber sportsN = new SportsNatsSubscriber(nc, latch);
//		NatsSubscriber execN = new ExecutionNatsSubscriber(nc, latch);

//		try {
//			latch.await();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
