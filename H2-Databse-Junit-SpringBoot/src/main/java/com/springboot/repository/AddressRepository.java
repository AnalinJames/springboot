package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Address;


public interface AddressRepository extends CrudRepository<Address ,Integer> {

}
