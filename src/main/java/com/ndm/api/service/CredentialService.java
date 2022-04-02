package com.ndm.api.service;

import com.ndm.api.dto.credential.CredentialRequestBody;
import com.ndm.api.entity.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> getAll();
    void add(final CredentialRequestBody requestBody);
    void update(final int id, final CredentialRequestBody requestBody);
    void delete(final int id);
}
