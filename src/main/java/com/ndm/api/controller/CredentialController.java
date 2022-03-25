package com.ndm.api.controller;

import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.CredentialsResponse;
import com.ndm.api.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialController {

    private final CredentialService credentialService;

    @Autowired
    public CredentialController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping(ApiPathConfig.GET_ALL_CREDENTIAL_URL)
    public CredentialsResponse getAll() {
        return new CredentialsResponse(credentialService.getAll());
    }
}
