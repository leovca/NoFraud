package br.nofraud.producer.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.nofraud.producer.domain.entity.CreditCardTransaction;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudDetectorServiceImp implements FraudDetectorService {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void transactionAnalysis(CreditCardTransaction transaction) throws IOException {
    ObjectMapper obj = new ObjectMapper();
    String transactionContent = obj.writeValueAsString(transaction);
    rabbitTemplate.convertAndSend("transaction", transactionContent);
  }


}
