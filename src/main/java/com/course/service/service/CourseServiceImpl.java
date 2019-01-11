package com.course.service.service;

import com.course.service.dao.CourseDao;
import com.course.service.exceptions.CourseCredentialsException;
import com.course.service.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourse() throws SQLException {
        return courseDao.getAllCourse();
    }

    @Override
    public void deleteCourse(int idCourse) throws CourseCredentialsException, SQLException {
        courseDao.deleteCourse(idCourse);
    }

}
