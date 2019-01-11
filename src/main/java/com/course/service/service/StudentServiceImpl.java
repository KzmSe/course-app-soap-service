package com.course.service.service;

import com.course.service.dao.StudentDao;
import com.course.service.exceptions.StudentCredentialsException;
import com.course.service.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }


    @Override
    public List<Student> getAllStudent() throws SQLException {
        return studentDao.getAllStudent();
    }

    @Override
    public void deleteStudent(int idStudent) throws SQLException, StudentCredentialsException {
        studentDao.deleteStudent(idStudent);
    }

    @Override
    public Student addStudent(Student student) throws SQLException {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(int id) throws SQLException, StudentCredentialsException {
        return studentDao.getStudentById(id);
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {
        return studentDao.updateStudent(student);
    }


}
