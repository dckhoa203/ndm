package com.ndm.api.util;

import com.ndm.api.common.ConstantCommon;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

public class Utils {
    /**
     * This a method get errors message from BindingResult
     * @param bindingResult BindingResult
     * @return message String
     */
    public static String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(ConstantCommon.DELIMITER_CHARACTER));
    }
}
