package com.ndm.api.service;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.credential.CredentialMapper;
import com.ndm.api.dto.credential.CredentialRequestBody;
import com.ndm.api.entity.Credential;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
import com.ndm.api.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.ndm.api.common.ConstantCommon.CREDENTIAL_NOT_FOUND;

/**
 * A class define credential service implement
 */
@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    public CredentialServiceImpl(final CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    /**
     * This is the method of get all credential
     * @return List<Credential>
     */
    @Override
    public List<Credential> getAll() {
        return credentialRepository.findAll();
    }

    /**
     * This is the method of adding a credential
     * @param requestBody CredentialRequestBody
     */
    @Override
    @Transactional
    public void add(final CredentialRequestBody requestBody) {
        final Credential credential = credentialMapper.mapToCredential(requestBody);
        if (credentialRepository.existsByName(credential.getName())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_NAME, credential.getName()));
        }

        if (credentialRepository.existsByUsername(credential.getUsername())) {
            throw new DuplicateException(String.format(ConstantCommon.DUPLICATE_USERNAME, credential.getUsername()));
        }

        credentialRepository.save(credential);
    }

    /**
     * This is the method of updating a credential
     * @param id CredentialRequest
     * @param requestBody CredentialRequestBody
     */
    @Override
    @Transactional
    public void update(final int id, final CredentialRequestBody requestBody) {
        final Credential credential = credentialMapper.mapToCredential(requestBody);
        credential.setId(id);
        final Credential newCredential = credentialRepository.getById(credential.getId());
        if (ObjectUtils.isEmpty(credential)) {
            throw new DataNotFoundException(CREDENTIAL_NOT_FOUND);
        }

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

    /**
     * This is the method of removing a credential
     * @param id int
     * @throws DataNotFoundException if the credential is not found
     */
    @Override
    @Transactional
    public void delete(final int id) {
        final Credential credential = credentialRepository.getById(id);
        if (ObjectUtils.isEmpty(credential)) {
            throw new DataNotFoundException(CREDENTIAL_NOT_FOUND);
        }
        credentialRepository.delete(credential);
    }
}
