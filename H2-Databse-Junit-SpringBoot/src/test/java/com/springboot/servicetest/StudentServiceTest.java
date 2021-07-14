package com.springboot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;
import com.springboot.service.StudentService;


@SpringBootTest
public class StudentServiceTest {
	
	@InjectMocks
	private StudentService studentService;
	
	@Mock
	private StudentRepository studentRepository;

	
	@Test
	@DisplayName("test for get method")
	void testGetAllStudents() {
		when(studentRepository.findAll()).thenReturn(Stream.of(new Student(12,"hasini","sumin",null,null),new Student(13,"manu","mohini",null,null))
				.collect(Collectors.toList()));
		assertEquals(2,studentService.getAllStudents().size());
	}
	
	@Test
	@DisplayName("test for getbyid method")
	public void testGetStudent() throws Exception {		
	Student student = new Student();			
	student.setId(1);		
	student.setFname("hasini");		
	student.setLname("sumin");			
	when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));	
	Student student1 = studentService.getStudent(1);
	assertEquals("hasini",student1.getFname());	
	}
	
	@Test
	@DisplayName("test for post method")
	public void testAddStudent() {
	Student student = new Student();
	student.setId(2);
	student.setFname("soni");
	student.setLname("alex");
	when(studentRepository.save(student)).thenReturn(student);
	Student student1 = studentService.addStudent(student);
	assertEquals(student1.getId(), student.getId());
	assertEquals(student1.getFname(), student.getFname());
	assertEquals(student1.getLname(), student.getLname());
	//verify(studentRepository,times(1)).save(student1);
	
	}
	
	
	@Test
	@DisplayName("test for delete method")
	public void testDeleteStudent() throws Exception {	
	Student student = new Student(1,"hasini","sumin",null,null);
	when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
	ResponseEntity<Student> s1 = studentService.deleteStudent(1);
	verify(studentRepository,times(1)).delete(student);
	}

}
