package com.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@NotNull
	@Size(min = 2, message = "First name should have atleast 2 characters")
	@Column
	private String fname;

	@NotNull
	@Size(min = 2, message = "Last name should have atleast 2 characters")
	@Column
	private String lname;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	private List<Book> books = new ArrayList<>();

	public Student(int id, String fname, String lname, Address address, List<Book> books) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.books = books;
	}

	public Student() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void removeBook(Book book) {
		this.books.remove(book);
	}

}
