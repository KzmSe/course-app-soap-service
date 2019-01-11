package com.course.service.soap;

import com.course.service.dao.CourseDao;
import com.course.service.dao.CourseDaoImpl;
import com.course.service.exceptions.CourseCredentialsException;
import com.course.service.model.Course;
import com.course.service.service.CourseServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public class CourseService {

    private com.course.service.service.CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());

    @WebMethod
    @WebResult(name = "course")
    public List<Course> getAllCourse() throws SQLException {
        List<Course> list = courseService.getAllCourse();
        return list;
    }

    @WebMethod
    public void deleteCourse(@WebParam(name = "id") int id) throws CourseCredentialsException, SQLException {
        courseService.deleteCourse(id);
    }

}
