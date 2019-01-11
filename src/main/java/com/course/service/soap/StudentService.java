package com.course.service.soap;

import com.course.service.dao.StudentDaoImpl;
import com.course.service.exceptions.StudentCredentialsException;
import com.course.service.model.Student;
import com.course.service.service.StudentServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public class StudentService {

    private com.course.service.service.StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());

    @WebMethod
    @WebResult(name = "student")
    public List<Student> getAllStudent() throws SQLException {
        List<Student> list = studentService.getAllStudent();
        return list;
    }

    @WebMethod
    public void deleteStudent(@WebParam(name = "id") int id) throws SQLException, StudentCredentialsException {
        studentService.deleteStudent(id);
    }

    @WebMethod
    @WebResult(name = "student")
    public Student addStudent(@WebParam(name = "student") Student student) throws SQLException {
        Student addedStudent = studentService.addStudent(student);
        return addedStudent;
    }

    @WebMethod
    @WebResult(name = "student")
    public Student getStudentById(@WebParam(name = "student") int id) throws SQLException, StudentCredentialsException {
        Student student = studentService.getStudentById(id);
        return student;
    }

    @WebMethod
    @WebResult(name = "student")
    public Student updateStudent(@WebParam(name = "student") Student student) throws SQLException {
        Student updatedStudent = studentService.updateStudent(student);
        return updatedStudent;
    }

}
