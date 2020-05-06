package br.nofraud.profilecontroller.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {

  private final ProfileService profileService;

  @RabbitListener(queues="profileUpdate")
  public void onMessage(Message message) throws JsonProcessingException {
    String creditCardId = new String(message.getBody(), StandardCharsets.UTF_8);
    log.debug("Atualizando profile: {}", creditCardId);
    profileService.updateModel(creditCardId);
  }

}
