package org.learning.firstproject.reopsitory;

import org.apache.ibatis.annotations.*;
import org.learning.firstproject.model.entities.Student;
import org.learning.firstproject.model.request.StudentRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM student")
    public List<Student> getStudent();
    @Select("SELECT * FROM student WHERE id = #{id}")
    public Student getStudentById(int id);

    @Insert("INSERT INTO student (name,gender,score1,score2,score3,total)" +
            " VALUES(#{stu.name},#{stu.gender},#{stu.score1},#{stu.score2},#{stu.score3},#{stu.total})")
    public void insertStudent(@Param("stu") StudentRequest studentRequest);

    @Update("UPDATE student SET name = #{stu.name},gender = #{stu.gender}," +
            "score1 = #{stu.score1},score2 = #{stu.score2},score3 = #{stu.score3},total = #{stu.total} WHERE id = #{stuId}")
    public void updateStudent(@Param("stu") StudentRequest studentRequest,@Param("stuId") int id);
}
