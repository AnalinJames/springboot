package com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.annotation.Postcode;

@Entity
public class Address {

	@Id
	@Column
	private int id;

	@NotNull
	@Size(min = 2, message = "Street name should have atleast 2 characters")
	@Column
	private String street;

	@Postcode
	private String postcode;

	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private Student student;

	public Address() {

	}

	public Address(int id, String street, String postcode, Student student) {
		super();
		this.id = id;
		this.street = street;
		this.postcode = postcode;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
