package vn.edu.hcmut.cse.adse.lab.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;;

@RestController 
@RequestMapping("/api/students") 
public class StudentController {
    @Autowired
    private StudentService service;

    // 1. API Lay danh sach: GET http://localhost:8080/api/students 
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    // 2. API Lay chi tiet: GET http://localhost:8080/api/students/{id} 
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        // Luu y: Can them method getById trong Service truoc 
        return service.getById(id);
    }

}