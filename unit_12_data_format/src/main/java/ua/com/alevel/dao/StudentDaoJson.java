package ua.com.alevel.dao;

import com.google.gson.Gson;
import ua.com.alevel.entity.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentDaoJson implements StudentDao {

    public StudentDaoJson() {
        System.out.println("StudentDaoJson.StudentDaoJson");
    }

    @Override
    public void create(Student student) {
        student.setId(UUID.randomUUID().toString());
        Gson gson = new Gson();
        try {
            StudentContainer container = gson.fromJson(new FileReader("students.json"), StudentContainer.class);
            List<Student> students = container.getStudents();
            students.add(student);
            container.setStudents(students);
            String json = gson.toJson(container);
            FileWriter fileWriter = new FileWriter("students.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<Student> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        Gson gson = new Gson();
        StudentContainer container = null;
        try {
            container = gson.fromJson(new FileReader("students.json"), StudentContainer.class);
            return container.getStudents();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StudentContainer {
        private List<Student> students;

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }
}
