package com.ndm.api.validation;

import com.ndm.api.validation.validator.MacAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * A class define annotation @MacAddress
 */
@Documented
@Constraint(validatedBy = MacAddressValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MacAddress {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isNull() default false;
}
