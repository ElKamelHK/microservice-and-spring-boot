package com.example.demo.students.controller;


import com.example.demo.students.model.Student;
import com.example.demo.students.repository.StudentRepository;
import com.example.demo.students.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/")
public class StudentController {
    private final StudentServices studentServices;
    @Autowired
    StudentController(StudentServices studentServices)
    {
        this.studentServices=studentServices;
    }

    public StudentRepository studentRepository;
    @GetMapping("students")
    List<Student> findAll(){
        return  studentServices.getAllStudents();
    }

    @PostMapping("addNewStudient")
    public void registerNewStudent(@RequestBody Student student)
    {
        this.studentServices.addNewStudent(student);
    }
    @DeleteMapping("dropStudent/{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long id)
    {
        studentServices.deleteStudentById(id);
    }
    @PutMapping("updateStudent/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name,@RequestParam(required = false) String email)
    {
        studentServices.updateStudent(id,name,email);
    }
}
