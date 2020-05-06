package br.nofraud.producer.application.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import br.nofraud.producer.domain.entity.CreditCardTransaction;
import br.nofraud.producer.domain.repository.CreditCardTransactionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardTransactionServiceImp implements CreditCardTransactionService {

  private final CreditCardTransactionRepository creditCardTransactionRepository;

  @Override
  public void save(CreditCardTransaction transaction) {
    creditCardTransactionRepository.save(transaction);
  }

  @Override
  public Optional<CreditCardTransaction> findById(String idTransaction) {
    return creditCardTransactionRepository.findById(idTransaction);
  }

}
