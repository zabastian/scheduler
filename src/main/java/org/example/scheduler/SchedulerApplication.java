package org.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

}
