package vn.edu.hcmut.cse.adse.lab.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() { 
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    public void save(Student student) {
        repository.save(student);
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

}