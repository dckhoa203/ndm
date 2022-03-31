package com.ndm.api.dto;

import com.ndm.api.validation.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceSearchRequest {
    private static final String IP_ADDRESS_INVALID_MESSAGE = "IP address must be in the correct format 000.000.000.000";

    @IpAddress(message = IP_ADDRESS_INVALID_MESSAGE)
    private String ipAddress;
}
