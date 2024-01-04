package com.jpa.api.project.service;

import java.util.List;

import com.jpa.api.project.dtos.StudentDto;

public interface StudentService {

	public StudentDto getStudentById(String studentId);

    public StudentDto saveStudent(StudentDto studentDto);

    public List<StudentDto> getStudents();
    
    public StudentDto updateStudent(String studentId, StudentDto updatedStudent);
    
    public void deleteStudentById(String studentId);
}
