package com.ndm.api.dto.ntpserver;

import com.ndm.api.validation.IpAddress;
import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * A class define NtpServerAddRequestBody
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerAddRequestBody {
    private static final String CLOCK_NAME_LENGTH_INVALID_MESSAGE = "Clock name length must be less than 100";
    private static final String IP_ADDRESS_INVALID_MESSAGE = "IP address must be in format 000.000.000.000";
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";
    private static final int CLOCK_NAME_LENGTH = 100;

    @Length(max = CLOCK_NAME_LENGTH, message = CLOCK_NAME_LENGTH_INVALID_MESSAGE)
    private String clockName;

    @IpAddress(message = IP_ADDRESS_INVALID_MESSAGE)
    private String ipAddress;

    @Number(message = DEVICE_ID_INVALID_MESSAGE)
    private String deviceId;
}
