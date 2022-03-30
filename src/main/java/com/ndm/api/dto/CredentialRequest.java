package com.ndm.api.dto;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a Credential request class
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialRequest {
    private static final String CREDENTIAL_ID_INVALID_MESSAGE = "Credential id must be numeric and greater than 0";

    @Number(message = CREDENTIAL_ID_INVALID_MESSAGE)
    private String id;
}
