package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.entity.Personal;

import java.util.List;

public interface PersonalService extends UserService<Personal> {

    Personal findByEmail(String email);
    List<Personal> findAllByListId(List<Long> ids);
}
