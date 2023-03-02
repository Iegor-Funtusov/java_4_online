package ua.com.alevel;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.dao.impl.EmployeeDaoImpl;
import ua.com.alevel.dto.DepartmentDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.DepartmentType;

import java.sql.Connection;
import java.util.Collection;
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
//        deleteEmployee();
        join();
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
        for (int i = 0; i < 20; i++) {
            Employee employee1 = new Employee();
            employee1.setFirstName("Name" + i);
            employee1.setLastName("Namenko" + i);
            employee1.setAge(20 + i);
            employeeDao.create(employee1);
        }
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

    private void join() {
//        Department department = departmentDao.findById(2L).get();
//        System.out.println("department = " + department.getId());
//        System.out.println("employees = " + department.getEmployees().size());

        Collection<DepartmentDto> departmentDtos = departmentDao.findDepartmentDto();
        for (DepartmentDto departmentDto : departmentDtos) {
            Department department = departmentDto.department();
            if (department != null) {
                DepartmentType departmentType = department.getDepartmentType();
                long count = departmentDto.employeeCount();
                System.out.println("departmentType = " + departmentType);
                System.out.println("count = " + count);
            }
        }
    }
}
