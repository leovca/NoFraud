package br.nofraud.transactionanalyses.infraestrucure.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

  @Bean
  public ObjectMapper modelMapper() {
    return new ObjectMapper();
  }
}
