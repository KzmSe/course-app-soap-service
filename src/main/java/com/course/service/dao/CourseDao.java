package com.course.service.dao;

import com.course.service.exceptions.CourseCredentialsException;
import com.course.service.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {

    List<Course> getAllCourse() throws SQLException;

    void deleteCourse(int idCourse) throws CourseCredentialsException, SQLException;

}
