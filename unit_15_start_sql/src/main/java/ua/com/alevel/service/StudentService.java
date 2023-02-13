package ua.com.alevel.service;

import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentService {

    void create(Student student);
    void update(Long Id, Student student);
    void delete(Long Id);
    Student findById(Long Id);
    Collection<Student> findAll();
}
