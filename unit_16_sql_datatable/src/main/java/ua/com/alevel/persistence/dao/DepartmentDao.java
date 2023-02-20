package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.DepartmentDto;
import ua.com.alevel.persistence.entity.Department;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentDao {

    void create(Department entity);
    void update(Department entity);
    void delete(Long id);
    Optional<Department> findById(Long id);
    Collection<Department> findAll();
    Collection<DepartmentDto> findEmpoleeCountByDepartmentType();
}
