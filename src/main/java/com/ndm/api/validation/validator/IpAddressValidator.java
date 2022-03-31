package com.ndm.api.validation.validator;

import com.ndm.api.validation.IpAddress;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

    private static final String DELIMITER_CHARACTER = "\\.";
    private static final int MAX_LENGTH = 4;
    private static final int MAX_NUMBER = 255;
    private static final int MIN_NUMBER = 0;

    private boolean isNull;

    @Override
    public void initialize(IpAddress ipAddress) {
        this.isNull = ipAddress.isNull();
    }

    /**
     * This is a method to check ip address
     * @param ipAddress String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid(final String ipAddress, final ConstraintValidatorContext context) {
        if (!StringUtils.hasText(ipAddress)) {
            return isNull;
        }

        final String[] ipComponents = ipAddress.split(DELIMITER_CHARACTER);

        if (ipComponents.length != MAX_LENGTH) {
            return false;
        }

        for (String ipComponent : ipComponents) {
            try {
                final int ipComponentNumber = Integer.parseInt(ipComponent);

                if (ipComponentNumber > MAX_NUMBER || ipComponentNumber < MIN_NUMBER) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
