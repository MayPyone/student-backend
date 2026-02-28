package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> student (){
        return studentRepository.findAll();
    }

    public void registerStudent (Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail((student.getEmail()));
        if(optionalStudent.isPresent()){
            throw  new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
        boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) {
            throw new IllegalStateException("No student found");

        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent (Student student, Long id) {
        Student student3 = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + student.getId() + " does not exist"
                ));


        student3.setName(student.getName());

        return student3;

    }
}
