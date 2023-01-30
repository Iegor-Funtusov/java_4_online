package ua.com.alevel.service;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.StudentDaoCsv;
import ua.com.alevel.dao.StudentDaoJson;
import ua.com.alevel.dto.StudentDto;
import ua.com.alevel.entity.Student;

import java.util.Collection;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoJson();

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
    public void update(String id, StudentDto dto) {

    }

    @Override
    public void delete(String id) {

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
