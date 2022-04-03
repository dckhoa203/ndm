package com.ndm.api.dto.intefaces;

import com.ndm.api.validation.IpAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceUpdateRequestBody {
    private static final String NAME_LENGTH_INVALID_MESSAGE = "Name length must be less than 100";
    private static final String IP_ADDRESS_INVALID_MESSAGE = "IP address must be in format 000.000.000.000";
    private static final String NETMASK_INVALID_MESSAGE = "Netmask must be in format 000.000.000.000";
    private static final String GATEWAY_INVALID_MESSAGE = "Gateway must be in format 000.000.000.000";
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";

    private static final int NAME_LENGTH = 100;

    @Length(max = NAME_LENGTH, message = NAME_LENGTH_INVALID_MESSAGE)
    private String name;
    private boolean state;
    private boolean dhcp;
    @IpAddress(message = IP_ADDRESS_INVALID_MESSAGE)
    private String ipAddress;
    @IpAddress(isNull = true, message = NETMASK_INVALID_MESSAGE)
    private String netmask;
    @IpAddress(isNull = true, message = GATEWAY_INVALID_MESSAGE)
    private String gateway;

    public boolean isNameEmpty() {
        return !StringUtils.hasText(name);
    }

    public boolean isNetmaskEmpty() {
        return !StringUtils.hasText(netmask);
    }

    public boolean isGatewayEmpty() {
        return !StringUtils.hasText(gateway);
    }
}
