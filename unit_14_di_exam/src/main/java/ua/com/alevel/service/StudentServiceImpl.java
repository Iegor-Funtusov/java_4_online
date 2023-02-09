package ua.com.alevel.service;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dto.StudentDto;
import ua.com.alevel.entity.Student;

import java.util.Collection;

@BeanClass
public class StudentServiceImpl implements StudentService {

    @InjectBean
    private StudentDao studentDao;

    @Override
    public void create(StudentDto studentDto) {
        Student student = new Student();
        student.setEmail(studentDto.email());
        student.setName(studentDto.name());
        student.setTelegramAcc(studentDto.telegramAcc());
        student.setGitHubAcc(studentDto.gitHubAcc());
        studentDao.create(student);
    }

    @Override
    public void update(String id, StudentDto dto) { }

    @Override
    public void delete(String id) {
        System.out.println("StudentServiceImpl.delete");
    }

    @Override
    public Student findById(String id) {
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return studentDao.findAll();
    }
}
