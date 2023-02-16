package ua.com.alevel.controller;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.Controller;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.annotations.Start;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

@Controller
@BeanClass
public class CrudControllerImpl implements CrudController {

    @InjectBean
    private EmployeeService employeeService;

    @InjectBean
    private DepartmentService departmentService;

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
        System.out.println("If you want create Employee, please enter 1");
        System.out.println("If you want find Employee, please enter 2");
        System.out.println("If you want delete Employee, please enter 3");
        System.out.println("If you want update Employee, please enter 4");
        System.out.println("If you want find all Employee, please enter 5");
        System.out.println("If you want create Department, please enter 6");
        System.out.println("If you want find Department, please enter 7");
        System.out.println("If you want delete Department, please enter 8");
        System.out.println("If you want update Department, please enter 9");
        System.out.println("If you want find all Department, please enter 10");
        System.out.println("If you want find all employees by department, please enter 11");
        System.out.println("If you want close application, please enter 12");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : createEmployee(reader); break;
            case "2" : findByIdEmployee(reader); break;
            case "3" : deleteEmployee(reader); break;
            case "4" : updateEmployee(reader); break;
            case "5" : findAllEmployee(); break;
            case "6" : createDepartment(reader); break;
            case "7" : findByIdDepartment(reader); break;
            case "8" : deleteDepartment(reader); break;
            case "9" : updateDepartment(reader); break;
            case "10" : findAllDepartment(); break;
            case "11" : findByDepartment(reader); break;
            case "12" : stop(); break;
        }
        menu();
    }

    private void findByDepartment(BufferedReader reader) throws IOException {
        System.out.println("findByDepartment");
        System.out.println("Please enter department id");
        String id = reader.readLine();
        Collection<Employee> employees = employeeService.findByDepartment(Long.parseLong(id));
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    private void findAllDepartment() {

    }

    private void updateDepartment(BufferedReader reader) {

    }

    private void deleteDepartment(BufferedReader reader) {

    }

    private void findByIdDepartment(BufferedReader reader) {

    }

    private void createDepartment(BufferedReader reader) {

    }

    private void findAllEmployee() {

    }

    private void updateEmployee(BufferedReader reader) {

    }

    private void deleteEmployee(BufferedReader reader) {

    }

    private void findByIdEmployee(BufferedReader reader) {

    }

    private void createEmployee(BufferedReader reader) {
        employeeService.create(new Employee());
    }

    private void stop() {
        System.exit(0);
    }
}
