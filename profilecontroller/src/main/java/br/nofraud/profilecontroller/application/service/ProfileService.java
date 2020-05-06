package br.nofraud.profilecontroller.application.service;

import br.nofraud.profilecontroller.domain.entity.ClientProfile;

public interface ProfileService {
  void save(ClientProfile profile);
  void updateModel(String creditCardId);
}
