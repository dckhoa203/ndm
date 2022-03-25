package com.ndm.api.service;

import com.ndm.api.entity.Credential;
import com.ndm.api.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialServiceImpl(final CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<Credential> getAll() {
        return credentialRepository.getAll();
    }
}
