package ua.com.alevel;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.dao.impl.EmployeeDaoImpl;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.DepartmentType;

import java.util.Optional;
import java.util.Set;

public class DepEmpCrud {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void start() {
//        createDepartament();
//        createEmployee();
//        findById();
//        attachEmployeeToDepartment();
        deleteEmployee();
    }

    private void createDepartament() {
        Department departmentJS = new Department();
        departmentJS.setDepartmentType(DepartmentType.JS);
        departmentDao.create(departmentJS);

        Department departmentJAVA = new Department();
        departmentJAVA.setDepartmentType(DepartmentType.JAVA);
        departmentDao.create(departmentJAVA);

        Department departmentSCALA = new Department();
        departmentSCALA.setDepartmentType(DepartmentType.SCALA);
        departmentDao.create(departmentSCALA);

        Department departmentDEV_OPS = new Department();
        departmentDEV_OPS.setDepartmentType(DepartmentType.DEV_OPS);
        departmentDao.create(departmentDEV_OPS);
    }

    private void createEmployee() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Name1");
        employee1.setLastName("Namenko1");
        employee1.setAge(20);
        employeeDao.create(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Name2");
        employee2.setLastName("Namenko2");
        employee2.setAge(22);
        employeeDao.create(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Name3");
        employee3.setLastName("Namenko3");
        employee3.setAge(23);
        employeeDao.create(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Name4");
        employee4.setLastName("Namenko4");
        employee4.setAge(24);
        employeeDao.create(employee4);
    }

    private void findById() {
        Department department = departmentDao.findById(1L).get();
        System.out.println("department = " + department);

        Employee employee = employeeDao.findById(1L).get();
        System.out.println("employee = " + employee);
    }

    private void attachEmployeeToDepartment() {
        Department department = departmentDao.findById(2L).get();
        Employee employee1 = employeeDao.findById(1L).get();
        Employee employee2 = employeeDao.findById(2L).get();
        Set<Employee> employees = department.getEmployees();
        employees.add(employee1);
        employees.add(employee2);
        departmentDao.update(department);
    }

    private void deleteEmployee() {
        Department department = departmentDao.findById(2L).get();
        Employee employee1 = employeeDao.findById(1L).get();
        Set<Employee> employees = department.getEmployees();
        employees.remove(employee1);
        departmentDao.update(department);
        employeeDao.delete(employee1);
    }
}
