package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adse.lab.service.StudentService; 
import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.*;

@Controller 
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students 
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        // 1. Lay du lieu tu Service
        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {
            // Can viet them ham searchByName trong Service/Repository 
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        // 2. Dong goi du lieu vao "Model" de chuyen sang View 
        // Key "dsSinhVien" se duoc su dung ben file HTML 
        model.addAttribute("dsSinhVien", students);

        // 3. Tra ve ten cua View (khong can duoi .html)
        // Spring Boot se tu dong tim file tai: src/main/resources/templates/students.html 
        return "students";

    }
    // Route: GET http://localhost:8080/students/create
    @GetMapping("/create")
    public String addStudent(Model model) {
        model.addAttribute("sinhVien", new Student());
        return "create";
    }

    @PostMapping("/create")
    public String saveNewStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) { 
        Student student = service.getById(id);
        model.addAttribute("sinhVien", student);
        return "id";
    }

    @GetMapping("/{id}/edit")
    public String modifyStudent(@PathVariable String id, Model model) { 
        Student student = service.getById(id);
        model.addAttribute("sinhVien", student);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String saveStudent(@PathVariable String id, @ModelAttribute Student student, Model model) { 
        service.save(student);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String removeStudent(@PathVariable String id, Model model) { 
        service.remove(id);
        return "redirect:/students";
    }

}