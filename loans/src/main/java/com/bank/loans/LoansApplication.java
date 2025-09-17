package com.bank.loans;

import com.bank.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan("com.bank.loans")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
@OpenAPIDefinition(
    info = @Info(
        title = "Loans Microservice",
        description = "Loans microservice API Documentation",
        version = "v1"
    )
)
public class LoansApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoansApplication.class, args);
  }

}
