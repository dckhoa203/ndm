package com.ndm.api.validation.validator;

import com.ndm.api.validation.MacAddress;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is a claas mac address validator class
 */
public class MacAddressValidator implements ConstraintValidator<MacAddress, String> {
    private static final String DELIMITER_CHARACTER = ":";
    private static final int MAX_LENGTH = 6;
    private static final int MAX_SUB_LENGTH = 2;

    private boolean isNull;

    @Override
    public void initialize(MacAddress macAddress) {
        this.isNull = macAddress.isNull();
    }

    /**
     * This is a method to check mac address
     * @param macAddress String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid(final String macAddress, final ConstraintValidatorContext context) {
        if (!StringUtils.hasText(macAddress)) {
            return isNull;
        }

        final String[] macComponents = macAddress.split(DELIMITER_CHARACTER);

        if (macComponents.length != MAX_LENGTH) {
            return false;
        }

        for (String macComponent : macComponents) {
            if (macComponent.length() != MAX_SUB_LENGTH ) {
                return false;
            }
        }

        return true;
    }
}
