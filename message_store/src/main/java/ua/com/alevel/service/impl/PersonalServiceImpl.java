package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.entity.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.web.data.PersistenceRequestData;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonalServiceImpl(
            PersonalRepository personalRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personalRepository = personalRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Personal personal) {
        if (personalRepository.existsByEmail(personal.getEmail())) {
            throw new RuntimeException("user exist");
        }
        personal.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
        personalRepository.save(personal);
    }

    @Override
    public void update(Personal personal) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Personal findById(Long id) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public Page<Personal> findAll(PersistenceRequestData persistenceRequestData) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return personalRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Personal findByEmail(String email) {
        return (Personal) personalRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public List<Personal> findAllByListId(List<Long> ids) {
        return personalRepository.findAllById(ids);
    }
}
