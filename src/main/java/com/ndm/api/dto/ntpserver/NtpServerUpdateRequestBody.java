package com.ndm.api.dto.ntpserver;

import com.ndm.api.validation.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerUpdateRequestBody {
    private static final String CLOCK_NAME_LENGTH_INVALID_MESSAGE = "Clock name length must be less than 100";
    private static final String IP_ADDRESS_INVALID_MESSAGE = "IP address must be in format 000.000.000.000";

    private static final int CLOCK_NAME_LENGTH = 100;

    @Length(max = CLOCK_NAME_LENGTH, message = CLOCK_NAME_LENGTH_INVALID_MESSAGE)
    private String clockName;

    @IpAddress(message = IP_ADDRESS_INVALID_MESSAGE)
    private String ipAddress;
}
