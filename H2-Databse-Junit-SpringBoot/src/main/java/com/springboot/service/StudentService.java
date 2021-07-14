package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.exception.StudentNotFoundException;
import com.springboot.model.Book;
import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}

	public Student getStudent(int id) throws Exception {
		return studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("User not found with id: " + id));
	}

	public Student addStudent(Student student) {
		return studentRepository.save(student);

	}

	public Student updateStudent(int id, Student student) throws Exception {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("User not found with id: " + id));
		existingStudent.setId(student.getId());
		existingStudent.setFname(student.getFname());
		existingStudent.setLname(student.getLname());
		return studentRepository.save(existingStudent);

	}

	public ResponseEntity<Student> deleteStudent(int id) throws Exception {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("User not found with id: " + id));
		studentRepository.delete(existingStudent);
		return ResponseEntity.ok().build();
	}

	public void createBookForStudent(List<Book> book, int studid) {
		Optional<Student> stud = studentRepository.findById(studid);
		if (stud.isPresent()) {
			Student student = stud.get();
			student.setBooks(book);
			for (Book books : book) {
				books.setStudent(student);

			}
			studentRepository.save(student);
		}
	}

	public List<Book> updateBookByStudentId(List<Book> book, int studentId) throws Exception {
		Optional<Student> student = studentRepository.findById(studentId);
		if (student.isPresent()) {
			Student newStudent = student.get();
			newStudent.setBooks(book);
			Student save = studentRepository.save(newStudent);
			return save.getBooks();
		}
		throw new StudentNotFoundException("Record not found with id : " + studentId);
	}

}
