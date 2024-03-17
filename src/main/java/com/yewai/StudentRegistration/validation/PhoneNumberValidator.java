package com.yewai.StudentRegistration.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final String PHONE_NUMBER_REGEX = "^[0-9]{11}$";

    @Override
    public boolean isValid(
            String phoneNumber,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        if (phoneNumber == null) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }
}
