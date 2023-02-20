package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistence.config.JdbcService;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.dto.DepartmentDto;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.type.DepartmentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@BeanClass
public class DepartmentDaoImpl implements DepartmentDao {

    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_STUDENT = "insert into departments values (default, ?, ?, ?)";
    private static final String UPDATE_STUDENT = "update departments set department_type = ? where id = ?";
    private static final String DELETE_STUDENT = "delete from departments where id = ?";
    private static final String FIND_STUDENT_BY_ID = "select * from departments where id = ";
    private static final String FIND_STUDENTS = "select * from departments";
    private static final String FIND_EMPOLEE_COUNT_BY_DEPARTMENT_TYPE = "select d.id, d.created, d.updated, d.department_type, count(dep_id) as employee_count\n" +
            "from departments as d left join dep_emp as de on d.id = de.dep_id group by d.id";

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
    public Optional<Department> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Department> findAll() {
        return null;
    }

    @Override
    public Collection<DepartmentDto> findEmpoleeCountByDepartmentType() {
        List<DepartmentDto> dtoList = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_EMPOLEE_COUNT_BY_DEPARTMENT_TYPE)) {
            while (resultSet.next()) {
                dtoList.add(generateDepartmentDto(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
            return Collections.emptyList();
        }
        return dtoList;
    }

    private DepartmentDto generateDepartmentDto(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String type = resultSet.getString("department_type");
        DepartmentType departmentType = DepartmentType.valueOf(type);
        int employeeCount = resultSet.getInt("employee_count");
        Department department = new Department();
        department.setId(id);
        department.setCreated(created);
        department.setUpdated(updated);
        department.setDepartmentType(departmentType);
        return new DepartmentDto(department, employeeCount);
    }
}
