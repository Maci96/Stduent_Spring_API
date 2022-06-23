package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student){
        Optional<Student> studentOptional= studentRepository.findStudentByEmail(
          student.getEmail()
        );
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email Taken");
            }
        studentRepository.save(student);
    }
    public void DeleteStudent(Long ID){
        boolean exists = studentRepository.existsById(ID);
        if(!exists){
            throw new IllegalStateException("student with id "+ID+" Does not exists");
        }
        else{
            studentRepository.deleteById(ID);
        }
    }
    @Transactional
    public void updateStudent(
         Long Id,String name, String email
    ){
    Student student = studentRepository.findById(Id)
            .orElseThrow(()->new IllegalStateException(
                    "Student id "+Id+" Doesn't exist"
        ));
    if(name != null && name.length()>0&&!Objects.equals(student.getName(), name)){
        student.setName(name);
    }
        if(email != null && email.length()>0&&!Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
    }

            }