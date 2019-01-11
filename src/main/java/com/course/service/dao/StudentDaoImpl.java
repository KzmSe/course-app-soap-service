package com.course.service.dao;

import com.course.service.constants.MessageConstants;
import com.course.service.exceptions.StudentCredentialsException;
import com.course.service.model.Course;
import com.course.service.model.Student;
import com.course.service.model.Teacher;
import com.course.service.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final String GET_ALL_STUDENT_SQL = "select s.id_student, s.first_name as s_first_name, s.last_name as s_last_name, s.date_of_birth, c.id_course, c.name as course_name, c.desc, c.duration, t.id_teacher, t.first_name as t_first_name, t.last_name as t_last_name from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on t.id_course=c.id_course";
    private final String DELETE_STUDENT = "delete from student where id_student=?";
    private final String ADD_STUDENT_SQL = "insert into student(first_name, last_name, date_of_birth, id_teacher) values(?,?,?,?)";
    private final String GET_STUDENT_BY_ID_SQL = "select s.id_student, s.first_name as s_first_name, s.last_name as s_last_name, s.date_of_birth, c.id_course, c.name as course_name, c.desc, c.duration, t.id_teacher, t.first_name as t_first_name, t.last_name as t_last_name from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on t.id_course=c.id_course where s.id_student = ?";
    private final String UPDATE_STUDENT_SQL = "update student set first_name = ?, last_name = ?, date_of_birth = ?, id_teacher = ? where id_student = ?";
    private final String CHECK_STUDENT_SQL = "select * from student where id_student = ?";

    @Override
    public List<Student> getAllStudent() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_STUDENT_SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("s_first_name"));
                student.setLastName(rs.getString("s_last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("course_name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));

                teacher.setCourse(course);
                student.setTeacher(teacher);

                list.add(student);
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    @Override
    public void deleteStudent(int idStudent) throws StudentCredentialsException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        if (!checkStudent(idStudent)) {
            throw new StudentCredentialsException(MessageConstants.ERROR_STUDENT_NOT_FOUND);
        }

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(DELETE_STUDENT);
            ps.setInt(1, idStudent);
            ps.executeUpdate();

        } finally {
            DbUtil.closeAll(con, ps);
        }
    }

    @Override
    public Student addStudent(Student student) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_STUDENT_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getDateOfBirth().toString());
            ps.setInt(4, student.getTeacher().getId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }

        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return student;
    }

    @Override
    public Student getStudentById(int id) throws StudentCredentialsException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_STUDENT_BY_ID_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("s_first_name"));
                student.setLastName(rs.getString("s_last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("course_name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));

                teacher.setCourse(course);
                student.setTeacher(teacher);

            } else {
                throw new StudentCredentialsException(MessageConstants.ERROR_STUDENT_NOT_FOUND);
            }

        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return student;
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(UPDATE_STUDENT_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getDateOfBirth().toString());
            ps.setInt(4, student.getTeacher().getId());
            ps.setInt(5, student.getId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }

        } finally {
            DbUtil.closeAll(con, ps);
        }

        return student;
    }

    //private methods
    private boolean checkStudent(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(CHECK_STUDENT_SQL);
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
