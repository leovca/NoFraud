package br.nofraud.profilecontroller.application.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.nofraud.profilecontroller.application.dto.ClientProfileRequest;
import br.nofraud.profilecontroller.application.service.ProfileService;
import br.nofraud.profilecontroller.domain.entity.ClientProfile;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class ProfileController {

  private final ProfileService profileService;
  private final ModelMapper modelMapper;

  @PostMapping(path = "/profile")
  public ResponseEntity save(
      @Valid @NotNull @RequestBody ClientProfileRequest request) {

    ClientProfile profile = modelMapper.map(request, ClientProfile.class);
    profileService.save(profile);

    return new ResponseEntity(HttpStatus.OK);
  }
}