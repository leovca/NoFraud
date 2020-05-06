package br.nofraud.transactionanalyses.infraestrucure.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.nofraud.transactionanalyses.domain.entity.ClientProfile;
import br.nofraud.transactionanalyses.domain.repository.ProfileRepository;

import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

   private final ProfileRepository profileRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<ClientProfile> profiles = profileRepository.findAll();

        if (profiles.isEmpty()) {
            createProfile("123", "BA", new BigDecimal(1000), new BigDecimal(2));
            createProfile("124", "SP", new BigDecimal(1000), new BigDecimal(3));
        }

    }

    public void createProfile(String creditCardNumber, String location, BigDecimal maximumValue,
        BigDecimal tolerance) {
        var profile = ClientProfile.builder()
            .creditCardNumber(creditCardNumber)
            .location(location)
            .maximumPayment(maximumValue)
            .tolerance(tolerance)
            .build();

        profileRepository.save(profile);
    }
}
