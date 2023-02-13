package ua.com.alevel.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@BeanClass
public class StudentDaoImpl implements StudentDao {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/java_4_online";
    private final String username = "root";
    private final String password = "Test123!";

    private Connection connection;
    private Statement statement;

    private static final String CREATE_STUDENT = "insert into students values (default, ?, ?, ?)";
    private static final String UPDATE_STUDENT = "update students set first_name = ?, last_name = ?, email = ? where id = ?";
    private static final String DELETE_STUDENT = "delete from students where id = ?";
    private static final String FIND_STUDENT_BY_ID = "select * from students where id = ";
    private static final String FIND_STUDENTS = "select * from students";

    public StudentDaoImpl() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void create(Student student) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void update(Student student) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setLong(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try(ResultSet resultSet = statement.executeQuery(FIND_STUDENT_BY_ID + id)) {
            while (resultSet.next()) {
                return Optional.of(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(ResultSet resultSet = statement.executeQuery(FIND_STUDENTS)) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return students;
        }
        return students;
    }

    private Student initStudentByResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        Long id = resultSet.getLong("id");
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        return student;
    }
}
