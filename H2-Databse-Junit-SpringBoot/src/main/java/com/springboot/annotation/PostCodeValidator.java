package com.springboot.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostCodeValidator implements ConstraintValidator<Postcode, String> {

	@Override
	public boolean isValid(String postcode, ConstraintValidatorContext context) {
		if (postcode == null || postcode.matches("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$") && postcode.length() > 4
				&& postcode.length() < 7) {
			return true;
		} else {
			return false;
		}
	}

}
