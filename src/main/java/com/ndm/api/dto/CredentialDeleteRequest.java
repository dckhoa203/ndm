package com.ndm.api.dto;

import com.ndm.api.validation.CheckNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialDeleteRequest {
    private static final String CREDENTIAL_ID_INVALID_MESSAGE = "Credential must be numeric and greater than 0";

    @CheckNumber(message = CREDENTIAL_ID_INVALID_MESSAGE)
    private String id;
}
