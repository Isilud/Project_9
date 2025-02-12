package com.medilabo.diagnosticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DiagnosticserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticserviceApplication.class, args);
	}

}
