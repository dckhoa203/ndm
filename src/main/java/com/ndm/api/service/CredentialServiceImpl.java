package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Credential;
import com.ndm.api.exception.DuplicateException;
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

    @Override
    public void add(final Credential credential) {
        if (credentialRepository.existsByName(credential.getName())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_NAME, credential.getName()));
        }

        if (credentialRepository.existsByUsername(credential.getUsername())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_USERNAME, credential.getUsername()));
        }

        credentialRepository.save(credential);
    }
}
