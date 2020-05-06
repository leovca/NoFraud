package br.nofraud.profilecontroller.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import br.nofraud.profilecontroller.domain.entity.ClientProfile;
import br.nofraud.profilecontroller.domain.repository.CreditCardTransactionRepository;
import br.nofraud.profilecontroller.domain.repository.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImp implements ProfileService {

  private final ProfileRepository profileRepository;
  private final CreditCardTransactionRepository creditCardTransactionRepository;

  @Override
  public void save(ClientProfile profile) {
    profileRepository.save(profile);
  }

  @Override
  public void updateModel(String creditCardNumber) {
    var profile = getProfileById(creditCardNumber);
    profile.ifPresent(p -> {
      var maxBuy = creditCardTransactionRepository
          .findTopByCreditCardNumberAndStatusOrderByValueDesc(creditCardNumber, "OK");
      p.setMaximumPayment(maxBuy.getValue());
      log.debug("Salvando perfil: {}", p);
      profileRepository.save(p);
    });
  }


  private Optional<ClientProfile> getProfileById(String creditCardNumber) {
    return profileRepository.findById(creditCardNumber);
  }
}
