package org.learning.firstproject.service;

import lombok.AllArgsConstructor;
import org.learning.firstproject.model.entities.Student;
import org.learning.firstproject.reopsitory.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getAllStudent(){
        return studentRepository.getStudent();
    }
}
