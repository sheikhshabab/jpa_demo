package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.Grade;
import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student/save")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentRepository.save(student));
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @GetMapping("/student/findByEmail")
    public Student findStudent(@RequestParam("email") String email) {
        return studentRepository.findByEmail(email);
    }

    @GetMapping("/student/findById")
    public Optional<Student> findStudentById(@RequestParam("id") Long id) {
        return studentRepository.findById(id);
    }

    @DeleteMapping("/student/deleteById")
    public String deleteStudentById(@RequestParam("id") Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
        }else{
            return "Student Not Found!";
        }
        return "Student Deleted Successfully!";
    }

    @PutMapping("/student/update")
    public String updateStudentById(@RequestParam("id") Long id, @RequestBody() Student data){
        Optional<Student> studentObj = studentRepository.findById(id);
        if(studentObj.isPresent()){
            Student student = studentRepository.findById(id).get();
            student.setName(data.getName());
            student.setEmail(data.getEmail());
            student.setContactNo(data.getContactNo());
            studentRepository.save(student);
        }else{
            return "Student Not Found!";
        }
        return "Student Updated Successfully!";
    }

    @PutMapping("/student/updateAddress")
    public Student updateStudentAddress(@RequestParam("id") Long id, @RequestBody Address newAddress){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student ID is not available!"));
        Address address = student.getAddress();
        address.setStreet(newAddress.getStreet());
        address.setCity(newAddress.getCity());
        address.setZipCode(newAddress.getZipCode());
        student.setAddress(address);
        studentRepository.save(student);
        return student;
    }

    @PutMapping("/student/updateGrade")
    public Student updateStudentGrade(@RequestParam("id") Long id, @RequestBody Grade newGrade) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student ID is not available!"));
        for (Grade grade : student.getGrades()) {
            if (grade.getId().equals(newGrade.getId())) {
                grade.setSubject(newGrade.getSubject());
                grade.setGrade(newGrade.getGrade());
                break;
            }
        }
        studentRepository.save(student);
        return student;
    }
}