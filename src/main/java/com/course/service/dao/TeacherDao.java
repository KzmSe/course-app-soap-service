package com.course.service.dao;

import com.course.service.exceptions.TeacherCredentialsException;
import com.course.service.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeacher() throws SQLException;

    void deleteTeacher(int idTeacher) throws TeacherCredentialsException, SQLException;

    List<Teacher> getTeachersByCourseId(int courseId) throws SQLException;

}
