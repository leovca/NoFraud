package br.nofraud.producer.application.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.nofraud.producer.application.dto.CreditCardPaymentRequest;
import br.nofraud.producer.application.dto.CreditCardPaymentResponse;
import br.nofraud.producer.application.service.CreditCardTransactionService;
import br.nofraud.producer.application.service.FraudDetectorService;
import br.nofraud.producer.domain.entity.CreditCardTransaction;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class CreditCardPaymentController {

  private final FraudDetectorService fraudDetectorService;
  private final CreditCardTransactionService creditCardTransactionService;
  private final ModelMapper modelMapper;


  @PostMapping(path = "/pay")
  public ResponseEntity<CreditCardPaymentResponse> pay(
      @Valid @NotNull @RequestBody CreditCardPaymentRequest request) throws Exception {

    CreditCardTransaction transaction = modelMapper.map(request, CreditCardTransaction.class);
    transaction.setTransactionKey(UUID.randomUUID().toString());
    transaction.setStatus("Em Avaliação");
    fraudDetectorService.transactionAnalysis(transaction);
    creditCardTransactionService.save(transaction);

    CreditCardPaymentResponse response = CreditCardPaymentResponse.builder()
        .transactionKey(transaction.getTransactionKey())
        .build();

    return new ResponseEntity<>(
        response,
        HttpStatus.OK);
  }


  @GetMapping(path = "/check/{id}")
  public ResponseEntity<String> checkTransactioStatus(@PathVariable("id") String idTransactoin) {
    Optional<CreditCardTransaction> result = creditCardTransactionService.findById(idTransactoin);
    return result.map(t -> new ResponseEntity<>(t.getStatus(),HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }
}
