package com.ndm.api.dto.port;

import com.ndm.api.validation.MacAddress;
import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * This class PortAddRequestBody
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortAddRequestBody {
    private static final String NAME_LENGTH_INVALID_MESSAGE = "Name length must be less than 100";
    private static final String CONNECTOR_LENGTH_INVALID_MESSAGE = "Connector length must be less than 100";
    private static final String SPEED_LENGTH_INVALID_MESSAGE = "Speed length must be less than 10";
    private static final String MTU_LENGTH_INVALID_MESSAGE = "Mtu length must be less than 10";
    private static final String MDI_LENGTH_INVALID_MESSAGE = "Mdi length must be less than 10";
    private static final String MAC_ADDRESS_INVALID_MESSAGE = "Mac address must be in format MM:MM:MM:SS:SS:SS.";
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";

    private static final int NAME_LENGTH = 100;
    private static final int CONNECTOR_LENGTH = 100;
    private static final int SPEED_LENGTH = 10;
    private static final int MTU_LENGTH = 10;
    private static final int MDI_LENGTH = 10;

    @Length(max = NAME_LENGTH, message = NAME_LENGTH_INVALID_MESSAGE)
    private String name;
    @Length(max = CONNECTOR_LENGTH, message = CONNECTOR_LENGTH_INVALID_MESSAGE)
    private String connector;
    private boolean state;
    @Length(max = SPEED_LENGTH, message = SPEED_LENGTH_INVALID_MESSAGE)
    private String speed;
    @Length(max = MTU_LENGTH, message = MTU_LENGTH_INVALID_MESSAGE)
    private String mtu;
    @Length(max = MDI_LENGTH, message = MDI_LENGTH_INVALID_MESSAGE)
    private String mdi;
    @MacAddress(message = MAC_ADDRESS_INVALID_MESSAGE)
    private String macAddress;
    @Number(message = DEVICE_ID_INVALID_MESSAGE)
    private String deviceId;
}
