package com.ndm.api.dto.intefaces;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceGetByDeviceRequest {
    private static final String DEVICE_ID_INVALID_MESSAGE = "Device id must be numeric and greater than 0";

    @Number(message = DEVICE_ID_INVALID_MESSAGE)
    private String deviceId;
}
