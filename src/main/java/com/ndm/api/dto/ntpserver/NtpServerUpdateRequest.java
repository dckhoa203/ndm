package com.ndm.api.dto.ntpserver;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerUpdateRequest {
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";
    private static final String CLOCK_NAME_REQUIRED_MESSAGE = "Clock name is required";
    private static final String CLOCK_NAME_LENGTH_INVALID_MESSAGE = "Clock name length must be less than 100";
    private static final int CLOCK_NAME_LENGTH = 100;

    @Number(message = DEVICE_ID_INVALID_MESSAGE)
    private String deviceId;

    @NotBlank(message = CLOCK_NAME_REQUIRED_MESSAGE)
    @Length(max = CLOCK_NAME_LENGTH, message = CLOCK_NAME_LENGTH_INVALID_MESSAGE)
    private String clockName;
}
