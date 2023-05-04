package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.Personal;

import java.util.List;

public interface PersonalService extends UserService<Personal> {

    boolean existsByEmail(String email);
    Personal findByEmail(String email);
    List<Personal> findAllByListId(List<Long> ids);
}
