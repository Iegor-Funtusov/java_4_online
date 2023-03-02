package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.DepartmentType;

import java.util.Set;

public class Department extends BaseEntity {

    private DepartmentType departmentType;

    private Set<Employee> employees;

    public Department() {
        super();
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + getId() + '\'' +
                "created='" + getCreated() + '\'' +
                "updated='" + getUpdated() + '\'' +
                "departmentType=" + departmentType +
                "} ";
    }
}
