package ua.com.alevel.service;

import ua.com.alevel.dto.StudentDto;
import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentService {

    void create(StudentDto dto);
    void update(String id, StudentDto dto);
    void delete(String id);
    Student findById(String id);
    Collection<Student> findAll();
}
