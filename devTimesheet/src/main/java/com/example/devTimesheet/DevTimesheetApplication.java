package com.example.devTimesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableAsync
public class DevTimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevTimesheetApplication.class, args);
	}

}
