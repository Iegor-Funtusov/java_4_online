package ua.com.alevel.controller;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.Controller;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.annotations.Start;
import ua.com.alevel.dto.StudentDto;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

@Controller
@BeanClass
public class StudentController implements MainController {

    @InjectBean
    private StudentService service;

    @Start
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
        System.out.println("If you want find all Students, please enter 4");
        System.out.println("If you want close application, please enter 5");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findByEmail(reader); break;
            case "3" : deleteByEmail(reader); break;
            case "4" : findAll(); break;
            case "5" : stop(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create Student");
        System.out.println("Please enter name");
        String name = reader.readLine();
        System.out.println("Please enter email");
        String email = reader.readLine();
        System.out.println("Please enter telegram");
        String telegram = reader.readLine();
        System.out.println("Please enter git hub");
        String gitHub = reader.readLine();
        StudentDto student = new StudentDto(name, email, gitHub, telegram);
        service.create(student);
    }

    private void findByEmail(BufferedReader reader) throws IOException {
        System.out.println("Find Student by id");
        String id = reader.readLine();
        Student student = service.findById(id);
        System.out.println("student = " + student);
    }

    private void deleteByEmail(BufferedReader reader) throws IOException {
        System.out.println("Delete Student by id");
        String id = reader.readLine();
        service.delete(id);
    }

    private void findAll() {
        System.out.println("Find all Students");
        Collection<Student> students = service.findAll();
        System.out.println("students = " + students);
    }

    private void stop() {
        System.exit(0);
    }
}
