package vn.edu.hcmut.cse.adse.lab.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmut.cse.adse.lab.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContaining(String keyword);
}
