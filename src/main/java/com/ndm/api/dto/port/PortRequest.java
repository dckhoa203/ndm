package com.ndm.api.dto.port;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortRequest {
    private static final String PORT_ID_INVALID_MESSAGE = "Port id must be numeric and greater than 0";

    @Number(message = PORT_ID_INVALID_MESSAGE)
    private String id;
}
