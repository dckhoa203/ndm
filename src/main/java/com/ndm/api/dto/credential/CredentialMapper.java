package com.ndm.api.dto.credential;

import com.ndm.api.entity.Credential;
import com.ndm.api.dto.credential.CredentialsResponse.CredentialResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class define deviceMapper
 */
public class CredentialMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CredentialMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This is a method convert Credential list to CredentialsResponse
     * @param credentials List<Credential>
     * @return CredentialsResponse
     */
    public CredentialsResponse mapToCredentialsResponse(final List<Credential> credentials) {
        if (credentials.isEmpty()) {
            return new CredentialsResponse(Collections.emptyList());
        }
        final List<CredentialResponse> credentialResponseList = credentials.stream().map(credential -> modelMapper.map(credential, CredentialResponse.class)).collect(Collectors.toList());
        return new CredentialsResponse(credentialResponseList);
    }

    /**
     * This is a method convert CredentialRequestBody to Credential
     * @param requestBody CredentialRequestBody
     * @return Credential
     */
    public Credential mapToCredential(final CredentialRequestBody requestBody) {
        if (ObjectUtils.isEmpty(requestBody)) {
            return null;
        }
        return modelMapper.map(requestBody, Credential.class);
    }
}
