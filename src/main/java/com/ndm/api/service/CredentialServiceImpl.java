package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.entity.Credential;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ndm.api.common.ConstantCommon.CREDENTIAL_NOT_FOUND;

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

    @Override
    public void update(final Credential credential) {
        Optional<Credential> optionalCredential = credentialRepository.findCredentialById(credential.getId());
        Credential newCredential = optionalCredential.orElseThrow(() -> new DataNotFoundException(CREDENTIAL_NOT_FOUND));

        if (credentialRepository.existsByNameNotById(credential.getName(), credential.getId())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_NAME, credential.getName()));
        }

        if (credentialRepository.existsByUsernameNotById(credential.getUsername(), credential.getId())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_USERNAME, credential.getUsername()));
        }

        newCredential.setName(credential.getName());
        newCredential.setUsername(credential.getUsername());
        newCredential.setPassword(credential.getPassword());
        credentialRepository.save(newCredential);
    }

    @Override
    public void delete(final int id) {
        Optional<Credential> optionalCredential = credentialRepository.findCredentialById(id);
        Credential credential = optionalCredential.orElseThrow(() -> new DataNotFoundException(CREDENTIAL_NOT_FOUND));
        credential.setDeleted(true);
        credentialRepository.save(credential);
    }
}
