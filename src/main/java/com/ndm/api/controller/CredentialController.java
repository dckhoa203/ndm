package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.CredentialDeleteRequest;
import com.ndm.api.dto.CredentialRequestBody;
import com.ndm.api.dto.CredentialsResponse;
import com.ndm.api.dto.Success;
import com.ndm.api.entity.Credential;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class CredentialController {

    private static final String DELIMITER_CHARACTER = ", ";

    private final CredentialService credentialService;

    @Autowired
    public CredentialController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping(ApiPathConfig.GET_ALL_CREDENTIAL_URL)
    public CredentialsResponse getAll() {
        return new CredentialsResponse(credentialService.getAll());
    }

    @PostMapping(ApiPathConfig.ADD_CREDENTIAL_URL)
    public Success add(@Valid @RequestBody final CredentialRequestBody request,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }

        final Credential credential = Credential.builder()
                                                .name(request.getName())
                                                .username(request.getUsername())
                                                .password(request.getPassword())
                                                .build();
        credentialService.add(credential);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "credential"));
    }

    @DeleteMapping(ApiPathConfig.DELETE_CREDENTIAL_URL )
    private Success delete(@Valid final CredentialDeleteRequest request,
                           final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }
        credentialService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }

    private String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(DELIMITER_CHARACTER));
    }
}
