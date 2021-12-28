package eu.epptec.carStop.validator;

import eu.epptec.carStop.validator.annotation.PasswordMatches;
import eu.epptec.carStop.dto.user.UserPostDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final UserPostDTO user = (UserPostDTO) value;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
