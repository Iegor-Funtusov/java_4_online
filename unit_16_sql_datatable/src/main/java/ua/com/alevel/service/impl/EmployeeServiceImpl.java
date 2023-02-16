package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;

@BeanClass
public class EmployeeServiceImpl implements EmployeeService {

    @InjectBean
    private EmployeeDao employeeDao;

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }

    @Override
    public Collection<Employee> findByDepartment(Long depId) {
        return employeeDao.findByDepartment(depId);
    }
}
