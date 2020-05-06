package br.nofraud.producer.application.service;

import br.nofraud.producer.domain.entity.CreditCardTransaction;

import java.io.IOException;

public interface FraudDetectorService {

  void transactionAnalysis(CreditCardTransaction transaction) throws IOException;

}
