package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.DepartmentDto;
import ua.com.alevel.persistence.entity.Department;

import java.util.Collection;

public interface DepartmentService {

    void create(Department entity);
    void update(Department entity);
    void delete(Long id);
    Department findById(Long id);
    Collection<Department> findAll();
    Collection<DepartmentDto> findEmpoleeCountByDepartmentType();
}
