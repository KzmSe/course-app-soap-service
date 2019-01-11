package com.course.service.service;

import com.course.service.exceptions.CourseCredentialsException;
import com.course.service.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {

    List<Course> getAllCourse() throws SQLException;
    void deleteCourse(int idCourse) throws CourseCredentialsException, SQLException;

}
