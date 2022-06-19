package com.dom.edge;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dom.edge.NatsSubscriber;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class EdgeApplication {

	private static final Logger logger = LoggerFactory.getLogger(EdgeApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(EdgeApplication.class, args);

		Connection nc = null;

		try {
			logger.info("trying to connect");
			nc = Nats.connect("host.docker.internal:4222");
			logger.info("connected");
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Unable to connect to NATS server. Exiting.");
			System.exit(-1);
		}

		CountDownLatch latch = new CountDownLatch(1);

		NatsSubscriber sportsN = new SportsNatsSubscriber("sport_event", nc, latch);
		NatsSubscriber execN = new ExecutionNatsSubscriber("execution", nc, latch);

		try {
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
