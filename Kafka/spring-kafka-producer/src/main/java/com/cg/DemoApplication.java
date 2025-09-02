package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/*
 * 1.  ashujauhari@Ashus-MacBook-Air kafka_2.13-2.7.1 % bin/zookeeper-server-start.sh config/zookeeper.properties
 * 2.  Ashus-MacBook-Air kafka_2.13-2.7.1 % bin/kafka-server-start.sh config/server.properties
 * 3. After running producer, check the logs in consumer.
 * 
 * 
 */