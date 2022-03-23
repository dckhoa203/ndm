package com.ndm.api.validation.validator;

import com.ndm.api.validation.CheckNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNumberValidator implements ConstraintValidator<CheckNumber, String> {

    private static final int MIN_VALUE = 1;

    private boolean isNull;

    @Override
    public void initialize(CheckNumber checkNumber) {
        this.isNull = checkNumber.isNull();
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(number)) {
            return isNull;
        }
        try {
            return Integer.parseInt(number) >= MIN_VALUE;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
