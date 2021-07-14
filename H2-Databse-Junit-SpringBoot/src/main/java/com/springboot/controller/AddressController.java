package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Address;
import com.springboot.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	// creating a get mapping that retrieves all the students detail from the
	// database

	@GetMapping("/address")
	public List<Address> getAllAddress() {
		return addressService.getAllAddress();
	}

	// creating a get mapping that retrieves specific details
	@GetMapping("/address/{id}")
	public Address getAddress(@PathVariable("id") int id) throws Exception {
		return addressService.getAddress(id);
	}

	// creating a delete mapping that deletes a specific student details

	@DeleteMapping("/students/{st_id}/address/{ad_id}")
	private void deleteAccount(@PathVariable("st_id") int stId, @PathVariable("ad_id") int adId) throws Exception {
		addressService.deleteAddress(stId, adId);
	}

	// creating post mapping that post the student detail in the database

	@PostMapping("/students/{id}/address")
	private String saveAddress(@Valid @RequestBody Address address, @PathVariable int id) {
		addressService.create(address, id);
		return "{" + "\n" + "id:" + address.getId() + "," + "\n" + "street:" + address.getStreet() + "," + "\n"
				+ "postcode:" + address.getPostcode() + "}";
	}

	// creating put mapping that updates the book detail

	@PutMapping("/students/{id}/address")
	private String updateAddress(@Valid @RequestBody Address address, @PathVariable("id") int id) {
		addressService.update(address, id);
		return "{" + "\n" + "id:" + address.getId() + "," + "\n" + "street:" + address.getStreet() + "," + "\n"
				+ "postcode:" + address.getPostcode() + "}";
	}

}
