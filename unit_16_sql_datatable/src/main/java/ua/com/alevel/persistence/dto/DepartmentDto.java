package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Department;

public record DepartmentDto(Department department, int employeeCount) { }
