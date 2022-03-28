package com.ndm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialRequestBody {

    private static final String NAME_REQUIRED_MESSAGE = "Name is required";
    private static final String NAME_LENGTH_INVALID_MESSAGE = "Name length must be less than 100";
    private static final String USERNAME_REQUIRED_MESSAGE = "Username is required";
    private static final String USERNAME_LENGTH_INVALID_MESSAGE = "Username length must be less than 100";
    private static final String PASSWORD_REQUIRED_MESSAGE = "Password is required";
    private static final String PASSWORD_LENGTH_INVALID_MESSAGE = "Password length must be less than 255";

    private static final int NAME_LENGTH = 100;
    private static final int USERNAME_LENGTH = 100;
    private static final int PASSWORD_LENGTH = 255;

    @NotBlank(message = NAME_REQUIRED_MESSAGE)
    @Length(max = NAME_LENGTH, message = NAME_LENGTH_INVALID_MESSAGE)
    private String name;

    @NotBlank(message = USERNAME_REQUIRED_MESSAGE)
    @Length(max = USERNAME_LENGTH, message = USERNAME_LENGTH_INVALID_MESSAGE)
    private String username;

    @NotBlank(message = PASSWORD_REQUIRED_MESSAGE)
    @Length(max = PASSWORD_LENGTH, message = PASSWORD_LENGTH_INVALID_MESSAGE)
    private String password;
}
