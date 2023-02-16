package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Employee;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDao {

    void create(Employee entity);
    void update(Employee entity);
    void delete(Long id);
    Optional<Employee> findById(Long id);
    Collection<Employee> findAll();
    Collection<Employee> findByDepartment(Long depId);
}
