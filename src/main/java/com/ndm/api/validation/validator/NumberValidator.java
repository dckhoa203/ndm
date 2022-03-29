package com.ndm.api.validation.validator;

import com.ndm.api.validation.Number;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is a number validator class
 */
public class NumberValidator implements ConstraintValidator<Number, String> {

    private static final int MIN_VALUE = 1;

    private boolean isNull;

    @Override
    public void initialize(Number number) {
        this.isNull = number.isNull();
    }

    /**
     * This is a method to check number
     * @param number String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
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
