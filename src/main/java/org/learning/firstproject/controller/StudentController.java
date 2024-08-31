package org.learning.firstproject.controller;

import lombok.AllArgsConstructor;
import org.learning.firstproject.model.entities.Student;
import org.learning.firstproject.model.request.StudentRequest;
import org.learning.firstproject.model.response.StudentResponse;
import org.learning.firstproject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StudentController {
    private StudentService studentService;
    @GetMapping("/student")
    public ResponseEntity<StudentResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        StudentResponse studentResponse = StudentResponse.builder()
                .message("Get All students Successfully")
                .data(students)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponse<Student>> getStudentById(@PathVariable int id) {
//        System.out.println("ID = "+id);
        Student student = studentService.getStudentById(id);
        StudentResponse studentResponse = StudentResponse.builder()
                .data(student)
                .message("Get Student Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }
    @PostMapping("/addstudent")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest) {
//        System.out.println("Name = "+studentRequest.getName());
//        System.out.println("Gender = "+studentRequest.getGender());
//        System.out.println("Score1 = "+studentRequest.getScore1());
//        System.out.println("Score2 = "+studentRequest.getScore2());
//        System.out.println("Score3 = "+studentRequest.getScore3());
//        System.out.println("Total = "+studentRequest.getTotal());

        studentRequest.setTotal(studentRequest.getScore1() + studentRequest.getScore2() + studentRequest.getScore3());
        studentService.addStudent(studentRequest);

        StudentResponse studentResponse = StudentResponse.builder()
                .message("Add Student Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
//                .status(HttpStatus.CREATED)
                .status(HttpStatus.valueOf(201))
                .build();


        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(201));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse<Student>> updateStudent(@PathVariable int id,@RequestBody StudentRequest studentRequest) {
//        System.out.println("Name = "+studentRequest.getName());
//        System.out.println("Gender = "+studentRequest.getGender());
//        System.out.println("Score1 = "+studentRequest.getScore1());
//        System.out.println("Score2 = "+studentRequest.getScore2());
//        System.out.println("Score3 = "+studentRequest.getScore3());
//        System.out.println("Total = "+studentRequest.getTotal());
        studentRequest.setTotal(studentRequest.getScore1() + studentRequest.getScore2() + studentRequest.getScore3());
        Student student = studentService.updateStudent(studentRequest, id);

        StudentResponse studentResponse = StudentResponse.builder()
                .message("Update Student Successfully")
                .data(student)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.valueOf(200))
                .build();


        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }
}
