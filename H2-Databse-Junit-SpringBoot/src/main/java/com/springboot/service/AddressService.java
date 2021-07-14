package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.exception.StudentNotFoundException;
import com.springboot.model.Address;
import com.springboot.model.Student;
import com.springboot.repository.AddressRepository;
import com.springboot.repository.StudentRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	
	//getting all student records  
	public List<Address> getAllAddress() {  
		List<Address> addresses = new ArrayList<Address>();
		addressRepository.findAll()
		.forEach(address -> addresses.add(address));  
		return addresses;  
	}  


	//getting a specific record  
	public Address getAddress(int id) throws Exception {
		return addressRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("User not found with id: " + id));
	}


	//creating a record
	public Student create(Address address, int id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			Student s = student.get();
			s.setAddress(address);
			return studentRepository.save(s);
		} 
		
		else {
			return null;
		}

	}
	
	
	//updating a record 
	public Student update(Address address, int id) {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent())
			{
				Student s= student.get();
				s.setAddress(address);
				return studentRepository.save(s);
			}
		
		else {
				return null;
			 }
		
	}
	
	//deleting specific record
	public void deleteAddress(int stId, int adId) throws Exception {
		Optional<Student> student = studentRepository.findById(stId);
		if (student.isPresent()) 
			{
				Optional<Address> address = addressRepository.findById(adId);
				if (address.isPresent()) {
					addressRepository.delete(address.get());
					Student s = student.get();
					s.setAddress(null);
					studentRepository.save(s);
						}
			}
		
		else {
				throw new StudentNotFoundException("User not found with id : " + stId);
			}
		}

}
