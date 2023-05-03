package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.facade.AuthValidatorFacade;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.web.data.AuthData;

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final PersonalService personalService;

    public AuthValidatorFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthData data = (AuthData) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (data.getEmail().length() < 6 || data.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.authForm.email");
        }
        if (personalService.existsByEmail(data.getEmail())) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (data.getPassword().length() < 8 || data.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }

        if (!data.getPasswordConfirm().equals(data.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
