package com.ndm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A class define Error response
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Error {
    private int code;
    private String message;
}
