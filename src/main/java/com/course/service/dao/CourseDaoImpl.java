package com.course.service.dao;

import com.course.service.constants.MessageConstants;
import com.course.service.exceptions.CourseCredentialsException;
import com.course.service.model.Course;
import com.course.service.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private final String GET_ALL_COURSE_SQL = "select * from course";
    private final String DELETE_COURSE = "delete from course where id_course = ?";
    private final String GET_COURSE_BY_ID_SQL = "select * from course where id_course = ?";
    private final String CHECK_COURSE_SQL = "select * from course where id_course = ?";

    @Override
    public List<Course> getAllCourse() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_COURSE_SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                list.add(course);
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    @Override
    public void deleteCourse(int idCourse) throws CourseCredentialsException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        if (!checkCourse(idCourse)) {
            throw new CourseCredentialsException(MessageConstants.ERROR_COURSE_NOT_FOUND);
        }

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(DELETE_COURSE);
            ps.setInt(1, idCourse);
            ps.executeUpdate();

        } finally {
            DbUtil.closeAll(con, ps);
        }
    }

    //private methods
    private boolean checkCourse(int idCourse) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(CHECK_COURSE_SQL);
            ps.setInt(1, idCourse);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }

        } finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }

}
