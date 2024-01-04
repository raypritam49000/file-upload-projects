package com.jpa.api.project.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.api.project.dtos.StudentDto;
import com.jpa.api.project.models.Student;
import com.jpa.api.project.repository.StudentRepository;
import com.jpa.api.project.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

 
    public StudentDto getStudentById(String studentId) {
        return modelMapper.map(studentRepository.findById(studentId).get(), StudentDto.class);
    }

    public StudentDto saveStudent(StudentDto studentDto) {
        return modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class);
    }


    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }
    
    public void deleteStudentById(String studentId) {
        studentRepository.delete(studentRepository.findById(studentId).get());
    }
    
    public StudentDto updateStudent(String studentId, StudentDto updatedStudent) {
    	StudentDto studentDto = modelMapper.map(studentRepository.findById(studentId).get(), StudentDto.class);
		
    	Optional.ofNullable(updatedStudent.getFullName()).ifPresent(studentDto::setFullName);
    	Optional.ofNullable(updatedStudent.getCity()).ifPresent(studentDto::setCity);
    	Optional.ofNullable(updatedStudent.getEmail()).ifPresent(studentDto::setEmail);
    
    	
       return modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class);
    }

}
