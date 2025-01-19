package com.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Springdoc-openai",
				version = "1.0",
				description = "ラーメンECサイトのSpringdoc-openapiです"
		)
)
@SpringBootApplication
public class Ec202404aApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec202404aApplication.class, args);
	}

}
