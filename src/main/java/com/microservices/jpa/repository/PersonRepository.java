package com.microservices.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservices.model.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	
	
	 Page<Person> findAll(Pageable pagable);
	


	  // Enables the distinct flag for the query
	  List<Person> findDistinctPersonByLastnameOrFirstname(String lastname, String firstname);
	  List<Person> findPersonDistinctByLastnameOrFirstname(String lastname, String firstname);

	  // Enabling ignoring case for an individual property
	 List<Person> findByLastnameIgnoreCase(String lastname);
	  // Enabling ignoring case for all suitable properties
	 List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

	  // Enabling static ORDER BY for a query
	  List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
	  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);

}
