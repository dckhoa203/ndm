package com.ndm.api.dto.credential;

import com.ndm.api.entity.Credential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is the class that returns the credential list
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialsResponse {

    private List<CredentialResponse> results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CredentialResponse {
        private int id;
        private String name;
        private String username;
    }
}
