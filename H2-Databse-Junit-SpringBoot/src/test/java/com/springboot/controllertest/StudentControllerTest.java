package com.springboot.controllertest;

import static com.springboot.controllertest.JsonFormat.getJsonFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;
import com.springboot.service.StudentService;


@ExtendWith(SpringExtension.class)
@SpringBootTest

@AutoConfigureMockMvc
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;
   

	@Test
	@DisplayName("test for getall method")
	void testGetAllStudents() throws Exception {
		List<Student> student= new ArrayList<>();		
		student.add(new Student(1,"hansu","ballu",null,null));
		Mockito.when(studentService.getAllStudents()).thenReturn(student);
		String getRequestBody = getJsonFormat(student);
		RequestBuilder requestBuilder = get("/students")
                .accept(MediaType.APPLICATION_JSON);
		mockmvc.perform(requestBuilder).andExpect(status().isOk())
               .andExpect(content().json(getRequestBody));
		verify(studentService, atLeast(1)).getAllStudents();
	}

	@Test
	@DisplayName("test for getbyid method")
	void testGetStudent() throws Exception {
		Student student= new Student();
		student.setId(1);		
		student.setFname("hasini");		
		student.setLname("sumin");	
		String getRequestBody = getJsonFormat(student);
		Mockito.when(studentService.getStudent(student.getId())).thenReturn(student);
		RequestBuilder requestBuilder = get("/students/1")
				                        .accept(MediaType.APPLICATION_JSON);
		mockmvc.perform(requestBuilder).andExpect(status().isOk())
		                               .andExpect(content().json(getRequestBody));
		verify(studentService, atLeast(1)).getStudent(student.getId());
	}
	
	@Test
	@DisplayName("test for post method")
	void testAddStudent() throws Exception {
		Student student = new Student(1,"hasini","sumin",null,null);
		Mockito.when(studentService.addStudent(any())).thenReturn(student);
		String postRequestBody = getJsonFormat(student);
		RequestBuilder requestBuilder = post("/students").characterEncoding("UTF-8")
			.contentType(MediaType.APPLICATION_JSON).content(postRequestBody);
		mockmvc.perform(requestBuilder).andExpect(status().isOk());
		verify(studentService, times(1)).addStudent(any());
	}
	
	@Test
	@DisplayName("test for update method")
	public void testUpdateStudent() throws Exception {
		Student student = new Student(1, "hasini", "sumin", null,null);
		student.setId(1);
		student.setFname("anu");
		student.setLname("davis");
		Mockito.when(studentService.updateStudent(anyInt(),any())).thenReturn(student);
		String putRequestMethod = getJsonFormat(student);
		RequestBuilder requestBuilder = put("/students/1").characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON).content(putRequestMethod);
		mockmvc.perform(requestBuilder).andExpect(status().isOk());
		verify(studentService, times(1)).updateStudent(anyInt(),any());
	}
	
	
	

}
