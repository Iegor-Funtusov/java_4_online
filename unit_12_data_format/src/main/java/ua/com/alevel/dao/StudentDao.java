package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentDao {

    void create(Student student);
    void update(Student student);
    void delete(String id);
    Optional<Student> findById(String id);
    Collection<Student> findAll();
}
