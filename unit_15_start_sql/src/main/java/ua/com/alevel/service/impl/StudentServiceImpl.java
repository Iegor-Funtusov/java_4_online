package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.Collection;
import java.util.Optional;

@BeanClass
public class StudentServiceImpl implements StudentService {

    @InjectBean
    private StudentDao studentDao;

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Long id, Student student) {
        Optional<Student> optionalStudent = studentDao.findById(id);
        if (optionalStudent.isPresent()) {
            student.setId(id);
            studentDao.update(student);
        }
    }

    @Override
    public void delete(Long Id) {
        studentDao.delete(Id);
    }

    @Override
    public Student findById(Long Id) {
        return studentDao.findById(Id).get();
    }

    @Override
    public Collection<Student> findAll() {
        return studentDao.findAll();
    }
}
