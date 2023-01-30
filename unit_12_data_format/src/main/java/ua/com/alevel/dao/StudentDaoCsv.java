package ua.com.alevel.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentDaoCsv implements StudentDao {

//    private final String[] header = new String[] { "id", "name", "email", "gitHubAcc", "telega" };
    private List<String[]> students = new ArrayList<>();

    public StudentDaoCsv() {
        System.out.println("StudentDaoCsv.StudentDaoCsv");
//        students.add(header);
    }

    @Override
    public void create(Student student) {
        try(
                CSVReader reader = new CSVReader(new FileReader("students.csv"));
                CSVWriter writer = new CSVWriter(new FileWriter("students.csv", true))
        ) {
            students = reader.readAll();
            String[] st = new String[] {
                    UUID.randomUUID().toString(),
                    student.getName(),
                    student.getEmail(),
                    student.getGitHubAcc(),
                    student.getTelegramAcc()
            };
            students.add(st);
            writer.writeAll(students);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<Student> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
            try(
                    CSVReader reader = new CSVReader(new FileReader("students.csv"))
            ) {
                students = reader.readAll();
                List<Student> studentList = new ArrayList<>();
                for (String[] string : students) {
                    Student st = new Student();
                    st.setId(string[0]);
                    st.setName(string[1]);
                    st.setEmail(string[2]);
                    st.setGitHubAcc(string[3]);
                    st.setTelegramAcc(string[4]);
                    studentList.add(st);
                }
                return studentList;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
    }
}
