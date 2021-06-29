package com.example.demo.students.configurations;

import com.example.demo.students.model.Student;
import com.example.demo.students.repository.StudentRepository;
import com.example.demo.students.services.StudentServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfigurations {

    @Bean
    CommandLineRunner commandLineRunner(StudentServices studentServices){
        List myListStudents=new ArrayList();
        return  args->{
            Student ali=  new Student("ali","ali@gmail.com", LocalDate.of(1980,01,7));
            Student meryam=  new Student("meryam","meryam@gmail.com", LocalDate.of(2001,10,5));
            Student mimo=  new Student("mimo","mimo@gmail.com", LocalDate.of(2007,12,5));
            Student hamdi=  new Student("hamdi","hamdi@gmail.com", LocalDate.of(1992,10,5));
            studentServices.saveIfNotExist(List.of(ali,meryam,mimo,hamdi));

        };

    }
}
