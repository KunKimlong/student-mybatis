package org.learning.firstproject.controller;

import lombok.AllArgsConstructor;
import org.learning.firstproject.model.entities.Student;
import org.learning.firstproject.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    @GetMapping("/api/student")
    public List<Student> getStudents() {
        return studentService.getAllStudent();
    }
}
