package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.dto.DepartmentDto;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.service.DepartmentService;

import java.util.Collection;

@BeanClass
public class DepartmentServiceImpl implements DepartmentService {

    @InjectBean
    private DepartmentDao departmentDao;

    @Override
    public void create(Department entity) {

    }

    @Override
    public void update(Department entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public Collection<Department> findAll() {
        return null;
    }

    @Override
    public Collection<DepartmentDto> findEmpoleeCountByDepartmentType() {
        return departmentDao.findEmpoleeCountByDepartmentType();
    }
}
