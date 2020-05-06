package br.nofraud.transactionanalyses.application.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import br.nofraud.transactionanalyses.domain.CreditCardTransaction;
import br.nofraud.transactionanalyses.domain.enums.AnalyseResult;
import br.nofraud.transactionanalyses.domain.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {

  private final ProfileRepository profileRepository;

  @Override
  public AnalyseResult evalute(CreditCardTransaction transaction) {

    var profile = profileRepository.findById(transaction.getCreditCardNumber());

    return profile.
        map(p -> p.evaluteTransaction(transaction))
        .orElse(AnalyseResult.FRAUD);
  }
}
