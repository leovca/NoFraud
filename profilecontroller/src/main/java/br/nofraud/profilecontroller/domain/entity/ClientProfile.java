package br.nofraud.profilecontroller.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "clientProfile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfile {

  @Id
  private String creditCardNumber;
  private String location;
  private BigDecimal maximumPayment;
  private BigDecimal tolerance;

}
