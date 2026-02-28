package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents (){
        return studentService.student();
    }

    @PostMapping
    public  void registerStudent (@RequestBody Student student) {
         studentService.registerStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent (@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent (@PathVariable("studentId") Long id, @RequestBody  Student student) {
        return studentService.updateStudent(student, id);
    }


}
