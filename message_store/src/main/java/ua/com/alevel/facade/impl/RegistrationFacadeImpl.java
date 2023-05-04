package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.web.data.AuthData;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthData authData) {
        Personal personal = new Personal();
        personal.setEmail(authData.getEmail());
        personal.setPassword(authData.getPassword());
        personalService.create(personal);
    }
}
