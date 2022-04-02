package com.ndm.api.dto.ntp;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class define NtpServerAddRequest
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerAddRequest {
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";

    @Number(message = DEVICE_ID_INVALID_MESSAGE)
    private String deviceId;
}
