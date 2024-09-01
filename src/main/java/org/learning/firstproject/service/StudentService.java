package org.learning.firstproject.service;

import lombok.AllArgsConstructor;
import org.learning.firstproject.model.entities.Student;
import org.learning.firstproject.model.request.StudentRequest;
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
    public Student getStudentById(int id){
        return studentRepository.getStudentById(id);
    }
    public List<Student> getStudentByname(String name){
        return studentRepository.getStudentByName("%"+name+"%");
    }

    public void addStudent(StudentRequest studentRequest){
        studentRepository.insertStudent(studentRequest);
    }
    public Student updateStudent(StudentRequest studentRequest,int id){
        studentRepository.updateStudent(studentRequest,id);
        return studentRepository.getStudentById(id);
    }
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }
}
