package com.ndm.api.dto.intefaces;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceRequest {
    private static final String INTERFACE_ID_INVALID_MESSAGE = "Interface id must be numeric and greater than 0";

    @Number(message = INTERFACE_ID_INVALID_MESSAGE)
    private String id;
}
