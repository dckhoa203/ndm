package com.ndm.api.dto;

import com.ndm.api.entity.Credential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the class that returns the credential list
 */
@NoArgsConstructor
@Data
public class CredentialsResponse {

    private List<CredentialResponse> results;

    public CredentialsResponse(final List<Credential> credentials) {
        this.results = credentials.stream()
                                  .map(CredentialResponse::new)
                                  .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CredentialResponse {
        private int id;
        private String name;
        private String username;

        public CredentialResponse(final Credential credential) {
            this.id = credential.getId();
            this.name = credential.getName();
            this.username = credential.getUsername();
        }
    }
}
