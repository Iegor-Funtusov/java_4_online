package ua.com.alevel.controller;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.Controller;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.annotations.Start;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

@Controller
@BeanClass
public class StudentControllerImpl implements StudentController {

    @InjectBean
    private StudentService studentService;

    @Start
    @Override
    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create Student, please enter 1");
        System.out.println("If you want find Student, please enter 2");
        System.out.println("If you want delete Student, please enter 3");
        System.out.println("If you want update Student, please enter 4");
        System.out.println("If you want find all Students, please enter 5");
        System.out.println("If you want close application, please enter 6");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findById(reader); break;
            case "3" : delete(reader); break;
            case "4" : update(reader); break;
            case "5" : findAll(); break;
            case "6" : stop(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create Student");
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter email");
        String email = reader.readLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        studentService.create(student);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Update Student");
        System.out.println("Please enter id");
        String id = reader.readLine();
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter email");
        String email = reader.readLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        studentService.update(Long.parseLong(id), student);
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Find Student by id");
        String id = reader.readLine();
        Student student = studentService.findById(Long.parseLong(id));
        System.out.println("student = " + student);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Delete Student by id");
        String id = reader.readLine();
        studentService.delete(Long.parseLong(id));
    }

    private void findAll() {
        System.out.println("Find all Students");
        Collection<Student> students = studentService.findAll();
        System.out.println("students = " + students);
    }

    private void stop() {
        System.exit(0);
    }
}
