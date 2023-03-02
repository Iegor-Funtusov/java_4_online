package ua.com.alevel.dto;

import ua.com.alevel.entity.Department;

public record DepartmentDto(Department department, long employeeCount) { }
