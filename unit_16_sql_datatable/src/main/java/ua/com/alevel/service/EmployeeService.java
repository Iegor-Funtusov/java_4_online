package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Employee;

import java.util.Collection;

public interface EmployeeService {

    void create(Employee entity);
    void update(Employee entity);
    void delete(Long id);
    Employee findById(Long id);
    Collection<Employee> findAll();
    Collection<Employee> findByDepartment(Long depId);
}
