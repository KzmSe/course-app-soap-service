package com.course.service.dao;

import com.course.service.exceptions.StudentCredentialsException;
import com.course.service.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    List<Student> getAllStudent() throws SQLException;

    void deleteStudent(int idStudent) throws StudentCredentialsException, SQLException;

    Student addStudent(Student student) throws SQLException;

    Student getStudentById(int id) throws StudentCredentialsException, SQLException;

    Student updateStudent(Student student) throws SQLException;

}
