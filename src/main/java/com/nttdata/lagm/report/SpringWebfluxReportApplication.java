package com.nttdata.lagm.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringWebfluxReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxReportApplication.class, args);
	}

}
