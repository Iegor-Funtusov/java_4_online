package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.data.request.AuthData;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PersonalRepository personalRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(PersonalRepository personalRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personalRepository = personalRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registration(AuthData authData) {
        if (personalRepository.existsByEmail(authData.getEmail())) {
            throw new RuntimeException("user already exists");
        }
        Personal personal = new Personal();
        personal.setEmail(authData.getEmail());
        personal.setPassword(passwordEncoder.encode(authData.getPassword()));
        personalRepository.save(personal);
    }
}
