package com.parlonsdev.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parlonsdev.entities.Student;
import com.parlonsdev.exception.StudentNotFoundException;
import com.parlonsdev.message.ResponseMessage;
import com.parlonsdev.repository.StudentRepository;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {
	
	private final StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	// GET ALL STUDENTS FROM DATABASE
	@GetMapping(path = "/students")
	public ResponseEntity<?> getAllStudents(Pageable pageable){
		Page<Student> students = studentRepository.findAll(pageable);
		if(students.isEmpty())
			return new ResponseEntity<>(new ResponseMessage("No Student"), HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	// GET A SINGLE STUDENT FROM DATABASE
	@GetMapping(path = "/students/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable("studentId") Long studentId){
		Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student", "studentId", studentId));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	// DELETE STUDENT BY ID FROM DATABASE
	@DeleteMapping(path = "/students/{studentId}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") Long studentId){
		Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student", "studentId", studentId));
		studentRepository.delete(student);
		return new ResponseEntity<>(new ResponseMessage("Student (id = "+studentId+") has bean deleted successfully!"), HttpStatus.OK);
	}
	
	// SAVE NEW STUDENT FROM DATABASE
	@PostMapping(path = "/students")
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student){
		student.setStudentCode(UUID.randomUUID().toString());
		Student studentData = new Student(student.getFirstName(), student.getLastName(), student.getEmail(), student.getPhone(), 
				                                                    student.getStudentCode(), student.getImageUrl(), student.isRegister());
		studentRepository.save(studentData);
		return new ResponseEntity<>(new ResponseMessage("Student ("+student.getLastName()+" "+student.getFirstName()+") has bean added successfully!"), HttpStatus.CREATED);
	}
	
	// UPDATE STUDENT BY ID FROM DATABASE
	@PutMapping(path = "/students/{studentId}")
	public ResponseEntity<?> updateStudentById(@PathVariable("studentId") Long studentId, @Valid @RequestBody Student student){
		studentRepository.findById(studentId).map(studentData -> {
			studentData.setFirstName(student.getFirstName());
			studentData.setLastName(student.getLastName());
			studentData.setEmail(student.getEmail());
			studentData.setPhone(student.getPhone());
			studentData.setImageUrl(student.getImageUrl());
			studentData.setRegister(student.isRegister());
			return studentRepository.save(studentData);
		});
		return new ResponseEntity<>(new ResponseMessage("Student (id = "+studentId+") has bean updated successfully!"), HttpStatus.OK);
	}

}
