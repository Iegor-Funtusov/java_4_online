package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistence.config.JdbcService;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@BeanClass
public class EmployeeDaoImpl implements EmployeeDao {

    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_STUDENT = "insert into employees values (default, ?, ?, ?)";
    private static final String UPDATE_STUDENT = "update employees set set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_STUDENT = "delete from employees where id = ?";
    private static final String FIND_STUDENT_BY_ID = "select * from employees where id = ";
    private static final String FIND_STUDENTS = "select * from employees";
    private static final String FIND_ALL_EMPLOYEES_BY_DEPARTMENT = "select id, created, updated, first_name, last_name, age from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = ";

    @Override
    public void create(Employee entity) {
        System.out.println("entity = " + entity);
        Connection connection = this.jdbcService.getConnection();
        System.out.println("connection = " + connection);
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }

    @Override
    public Collection<Employee> findByDepartment(Long depId) {
        List<Employee> employees = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(FIND_ALL_EMPLOYEES_BY_DEPARTMENT + depId)) {
            while (resultSet.next()) {
                employees.add(generateEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return employees;
        }
        return employees;
    }

    private Employee generateEmployeeByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        Employee employee = new Employee();
        employee.setId(id);
        employee.setCreated(created);
        employee.setUpdated(updated);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        return employee;
    }
}
