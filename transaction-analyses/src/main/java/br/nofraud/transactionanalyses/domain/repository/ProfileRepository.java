package br.nofraud.transactionanalyses.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.nofraud.transactionanalyses.domain.entity.ClientProfile;

public interface ProfileRepository extends MongoRepository<ClientProfile, String> {

}
