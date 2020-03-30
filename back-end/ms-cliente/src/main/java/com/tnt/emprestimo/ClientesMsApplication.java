package com.tnt.emprestimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@Configuration
public class ClientesMsApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(ClientesMsApplication.class, args);
	}

}
