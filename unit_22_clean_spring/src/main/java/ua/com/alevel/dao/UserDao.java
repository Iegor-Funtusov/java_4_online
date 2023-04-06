package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void create(User user);
    void update(User user);
    void delete(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
}
