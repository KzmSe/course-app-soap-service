package com.course.service.dao;

import com.course.service.constants.MessageConstants;
import com.course.service.exceptions.TeacherCredentialsException;
import com.course.service.model.Course;
import com.course.service.model.Teacher;
import com.course.service.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    private final String GET_ALL_TEACHER_SQL = "select t.id_teacher, t.first_name, t.last_name, c.id_course, c.name, c.desc, c.duration from teacher t inner join course c on t.id_course=c.id_course";
    private final String DELETE_TEACHER = "delete from teacher where id_teacher=?";
    private final String GET_ALL_TEACHERS_BY_COURSE_ID_SQL = "select * from teacher where id_course = ?";
    private final String CHECK_TEACHER_SQL = "select * from teacher where id_teacher = ?";

    @Override
    public List<Teacher> getAllTeacher() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_TEACHER_SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setCourse(course);

                list.add(teacher);
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    @Override
    public void deleteTeacher(int idTeacher) throws TeacherCredentialsException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        if (!checkTeacher(idTeacher)) {
            throw new TeacherCredentialsException(MessageConstants.ERROR_TEACHER_NOT_FOUND);
        }

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(DELETE_TEACHER);
            ps.setInt(1, idTeacher);
            ps.executeUpdate();

        } finally {
            DbUtil.closeAll(con, ps);
        }
    }

    @Override
    public List<Teacher> getTeachersByCourseId(int courseId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_TEACHERS_BY_COURSE_ID_SQL);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));

                list.add(teacher);
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    //private methods
    private boolean checkTeacher(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(CHECK_TEACHER_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return result;
    }

}
