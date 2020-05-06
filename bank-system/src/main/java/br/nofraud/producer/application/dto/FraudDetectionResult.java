package br.nofraud.producer.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudDetectionResult {
  private String transactionKey;
  private String status;
}
