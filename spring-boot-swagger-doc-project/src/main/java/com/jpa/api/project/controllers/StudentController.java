package com.jpa.api.project.controllers;

import com.jpa.api.project.dtos.StudentDto;
import com.jpa.api.project.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Controller", description = "APIs For Student Crud!!")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public StudentDto getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public StudentDto saveStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @PutMapping("/{studentId}")
    public StudentDto updateStudent(@PathVariable String studentId, @RequestBody StudentDto updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable String studentId) {
        studentService.deleteStudentById(studentId);
    }
}
