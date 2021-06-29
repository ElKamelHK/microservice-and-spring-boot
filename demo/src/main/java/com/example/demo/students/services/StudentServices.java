package com.example.demo.students.services;


import com.example.demo.students.model.Student;
import com.example.demo.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServices( StudentRepository studentRepository) {
    this.studentRepository=studentRepository;
    }

    public  List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }
    public void saveIfNotExist(List<Student> studentList)
    {
        for(int i=0;i<studentList.size();i++)
        {
            if(!studentRepository.existsByEmail(studentList.get(i).getEmail()))
                studentRepository.save(studentList.get(i));
        }

    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalByEmail = studentRepository
                .findStudentByEmail(student.getEmail());

        if(optionalByEmail.isPresent())
            throw  new IllegalStateException("email taken");

        studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        boolean existsById = studentRepository.existsById(id);
        if(!existsById)
        {
            throw  new IllegalStateException("user with id "+id+"is not found");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        boolean existsById = studentRepository.existsById(id);
       // this method userd if we don't will used Object
        /*if(!existsById)
            throw  new IllegalStateException("user not found with this id "+id);*/
        Student student= studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("user not found with this id "+id));
        if((name!=null) && (name.length()>0)&& (!Objects.equals(student.getName(),name)))
        {
            student.setName(name);
        }
        if((email!=null) && (email.length()>0) && (!Objects.equals(student.getEmail(),email)))
        {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent())
                throw    new IllegalStateException("email taken");
            student.setEmail(email);
        }

    }
}
