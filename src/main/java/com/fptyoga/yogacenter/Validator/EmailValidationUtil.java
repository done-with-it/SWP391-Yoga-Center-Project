package com.fptyoga.yogacenter.Validator;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidationUtil {
    
    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
