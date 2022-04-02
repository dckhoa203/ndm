package com.ndm.api.dto.device;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class define DeviceGetByTypeRequest
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceGetByTypeRequest {
    private static final String TYPE_INVALID_MESSAGE = "Type must be numeric and greater than 0";

    @Number(message = TYPE_INVALID_MESSAGE)
    private String type;
}
