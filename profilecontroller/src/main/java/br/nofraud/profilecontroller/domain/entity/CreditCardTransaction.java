package br.nofraud.profilecontroller.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("transaction")
public class CreditCardTransaction {

  @Id
  private String transactionKey;
  private BigDecimal value;
  private String creditCardNumber;
  private String merchantId;
  private String paymentLocation;
  private String status;

}
