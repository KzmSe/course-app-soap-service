package com.course.service.soap;

import com.course.service.dao.TeacherDaoImpl;
import com.course.service.exceptions.TeacherCredentialsException;
import com.course.service.model.Teacher;
import com.course.service.service.TeacherServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public class TeacherService {

    private com.course.service.service.TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());

    @WebMethod
    @WebResult(name = "teacher")
    public List<Teacher> getAllTeacher() throws SQLException {
        List<Teacher> list = teacherService.getAllTeacher();
        return list;
    }

    @WebMethod
    public void deleteTeacher(@WebParam(name = "id") int id) throws SQLException, TeacherCredentialsException {
        teacherService.deleteTeacher(id);
    }

    @WebMethod
    @WebResult(name = "teacher")
    public List<Teacher> getTeachersByCourseId(@WebParam(name = "id") int id) throws SQLException {
        List<Teacher> list = teacherService.getTeachersByCourseId(id);
        return list;
    }

}
