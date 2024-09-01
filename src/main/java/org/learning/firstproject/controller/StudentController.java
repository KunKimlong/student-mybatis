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
        if(students.isEmpty()){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(404));
        }
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
//      System.out.println("ID = "+id);
        Student student = studentService.getStudentById(id);
        if(student == null){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(404));
        }
        StudentResponse studentResponse = StudentResponse.builder()
                .data(student)
                .message("Get Student Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }

    @GetMapping("/student/name")
    public ResponseEntity<StudentResponse<List<Student>>> getStudentByName(@RequestParam String name) {
        List<Student> students = studentService.getStudentByname(name);
        if(students.isEmpty()){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(404));
        }
        StudentResponse studentResponse = StudentResponse.builder()
                .message("Get All students Successfully")
                .data(students)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }

    @PostMapping("/addstudent")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest) {
        if(studentRequest.getName() != null && studentRequest.getName()!=null
                && studentRequest.getScore1()!=0.0 && studentRequest.getScore2()!=0.0
                && studentRequest.getScore3()!=0.0
        ){
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
        else{
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("Add Student not Successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse<Student>> updateStudent(@PathVariable int id,@RequestBody StudentRequest studentRequest) {
        Student chceckStudent = studentService.getStudentById(id);
        if(chceckStudent == null){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(404));
        }

        if(
                !(studentRequest.getName() != null && studentRequest.getName()!=null
                        && studentRequest.getScore1()!=0.0 && studentRequest.getScore2()!=0.0
                        && studentRequest.getScore3()!=0.0)
        ){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("Add Student not Successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST);
        }

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id){
        Student chceckStudent = studentService.getStudentById(id);
        if(chceckStudent == null){
            StudentResponse studentResponse = StudentResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(404));
        }
        studentService.deleteStudent(id);
        StudentResponse studentResponse = StudentResponse.builder()
                .message("Delete Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.valueOf(200))
                .build();
        return new ResponseEntity<>(studentResponse, HttpStatus.valueOf(200));
    }
}
