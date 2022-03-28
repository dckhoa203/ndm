package com.ndm.api.service;

import com.ndm.api.dto.CredentialRequest;
import com.ndm.api.dto.CredentialRequestBody;
import com.ndm.api.entity.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> getAll();
    void add(final CredentialRequestBody requestBody);
    void update(final CredentialRequest request, final CredentialRequestBody requestBody);
    void delete(final int id);
}
