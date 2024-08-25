package org.learning.firstproject.reopsitory;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.learning.firstproject.model.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM student")
    public List<Student> getStudent();
}
