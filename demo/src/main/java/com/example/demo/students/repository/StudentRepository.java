package com.example.demo.students.repository;


import com.example.demo.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour la gestion de rcud 
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findStudentByEmail(String email);
    boolean existsByEmail(String email);
}
