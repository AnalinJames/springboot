package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Book;
import com.springboot.model.Student;
import com.springboot.service.StudentService;

@RestController
public class StudentController {

	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@RequestMapping("/students")
	public List<Student> getAllStudents() {
		logger.info("Get Method is accessed");
		return studentService.getAllStudents();
	}

	@RequestMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) throws Exception {
		logger.info("GetById Method is accessed");
		return studentService.getStudent(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/students")
	public void addStudent(@Valid @RequestBody Student student) {
		logger.info("Post Method is accessed");
		studentService.addStudent(student);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
	public void updateStudent(@Valid @RequestBody Student student, @PathVariable int id) throws Exception {
		logger.info("Put Method is accessed");
		studentService.updateStudent(id, student);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
	public void deleteStudent(@PathVariable int id) throws Exception {
		logger.info("Delete Method is accessed");
		studentService.deleteStudent(id);
	}

	@PostMapping("students/{studentid}/book")
	public ResponseEntity<List<Book>> createBookForStudent(@RequestBody List<Book> books,
			@PathVariable("studentid") int stuid) {
		studentService.createBookForStudent(books, stuid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/students/{studentid}/book")
	public List<Book> updateBookByStudentId(@RequestBody List<Book> book, @PathVariable int studentId)
			throws Exception {
		return studentService.updateBookByStudentId(book, studentId);
	}

}
