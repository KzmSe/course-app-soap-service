package com.course.service.service;

import com.course.service.dao.TeacherDao;
import com.course.service.exceptions.TeacherCredentialsException;
import com.course.service.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService{
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeacher() throws SQLException {
        return teacherDao.getAllTeacher();
    }

    @Override
    public void deleteTeacher(int idTeacher) throws SQLException, TeacherCredentialsException {
        teacherDao.deleteTeacher(idTeacher);
    }

    @Override
    public List<Teacher> getTeachersByCourseId(int courseId) throws SQLException {
        return teacherDao.getTeachersByCourseId(courseId);
    }

}
