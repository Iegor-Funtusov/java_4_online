package ua.com.alevel.persistence.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistence.config.JdbcService;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.entity.Department;

import java.util.Collection;
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
}
