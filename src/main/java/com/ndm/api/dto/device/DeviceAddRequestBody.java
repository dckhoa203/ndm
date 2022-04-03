package com.ndm.api.dto.device;

import com.ndm.api.validation.IpAddress;
import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * A class define DeviceAddRequestBody
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceAddRequestBody {

    private static final String LABEL_LENGTH_INVALID_MESSAGE = "Label length must be less than 100";
    private static final String IP_ADDRESS_INVALID_MESSAGE = "IP address must be in format 000.000.000.000";
    private static final String PORT_INVALID_MESSAGE = "Port must be numeric and greater than 0";
    private static final String CREDENTIAL_ID_INVALID_MESSAGE = "Credential id must be numeric and greater than 0";

    private static final int LABEL_LENGTH = 100;

    @Length(max = LABEL_LENGTH, message = LABEL_LENGTH_INVALID_MESSAGE)
    private String label;
    @IpAddress(message = IP_ADDRESS_INVALID_MESSAGE)
    private String ipAddress;
    @Number(message = PORT_INVALID_MESSAGE)
    private String port;
    @Number(message = CREDENTIAL_ID_INVALID_MESSAGE)
    private String credentialId;
}
