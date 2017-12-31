
package com.microservices.serviceslayer;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservices.jpa.repository.PersonRepository;
import com.microservices.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	private CounterService counterService;

	@Override
	public Person save(Person person) {

		counterService.increment("counter.save.success");
		return personRepository.save(person);

	}

	@Override
	public void delete(Serializable id) {

		counterService.increment("counter.delete.success");
		personRepository.delete((Long) id);

	}

	@Override
	public Page<Person> findAll(Integer pageNumber, Integer size) {
		counterService.increment("counter.findall.success");
		return  personRepository.findAll(new PageRequest(pageNumber, size));
	}

	@Override
	public Person findById(Serializable id) {
		counterService.increment("counter.findById.success");
		return personRepository.findOne((Long) id);
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return (List<Person>) personRepository.findAll();
	}

	@Override
	public List<Person> findByLastnameIgnoreCase(Serializable id) {
		// TODO Auto-generated method stub
		return personRepository.findByLastnameIgnoreCase(id.toString());
	}

}
