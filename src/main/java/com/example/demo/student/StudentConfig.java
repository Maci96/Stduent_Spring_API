package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MARCH;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student mariam = new Student(

                    "Maryam",
                    "Mariam.jamal@gmail.com",
                    LocalDate.of(1994, JANUARY+1, 2)

            );
            Student alex =new Student(
                    "Alex",
                    "Alex.Hound@gmail.com",
                    LocalDate.of(2000,MARCH+1 , 5)
            );
            studentRepository.saveAll(List.of(mariam, alex));
        };

    }
}
