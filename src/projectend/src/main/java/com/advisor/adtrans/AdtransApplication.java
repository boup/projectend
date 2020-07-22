package com.advisor.adtrans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
//@EnableJpaAuditing
@EnableJpaRepositories
@ComponentScan({"com.advisor.adtrans.controller"})
public class AdtransApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AdtransApplication.class, args);
      //  new Parser().parse(false);
	}

}
