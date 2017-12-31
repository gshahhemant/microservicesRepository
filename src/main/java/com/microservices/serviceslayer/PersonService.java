package com.microservices.serviceslayer;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.microservices.model.Person;

public interface PersonService extends SuperService<Person> {
	public Page<Person> findAll(Integer pageNumber,Integer size);
	public List<Person> findByLastnameIgnoreCase(Serializable id);
		
}
