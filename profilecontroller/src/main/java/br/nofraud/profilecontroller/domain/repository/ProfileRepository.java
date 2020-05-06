package br.nofraud.profilecontroller.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.nofraud.profilecontroller.domain.entity.ClientProfile;

public interface ProfileRepository extends MongoRepository<ClientProfile, String> {

}
