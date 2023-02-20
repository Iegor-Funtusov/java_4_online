package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.DepartmentType;

public class Department extends BaseEntity {

    private DepartmentType departmentType;

    public Department() {
        super();
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
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
