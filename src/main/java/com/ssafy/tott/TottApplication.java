package com.ssafy.tott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TottApplication {

  public static void main(String[] args) {
    SpringApplication.run(TottApplication.class, args);
  }
}
