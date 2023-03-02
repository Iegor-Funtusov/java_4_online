package ua.com.alevel.dao;

import ua.com.alevel.dto.DepartmentDto;
import ua.com.alevel.entity.Department;

import java.util.Collection;

public interface DepartmentDao extends BaseDao<Department> {

    Collection<DepartmentDto> findDepartmentDto();
}
