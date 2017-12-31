package com.microservices.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.model.Person;
import com.microservices.serviceslayer.PersonService;

@RestController
@RequestMapping(value = "/services/v2")
public class PersonController {
	static Logger logger = Logger.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/createPerson", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		logger.info("createPerson::Start");
		personService.save(person);
		logger.debug("PersonCreate" + person);
		return new ResponseEntity<Person>(person, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/updatePerson", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		logger.info("updatePerson::Start");

		Person existingPerson = personService.findById(person.getId());
		if (existingPerson != null) {
			personService.save(person);
			logger.info("updatePerson::personUpdated");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			logger.debug("unable to find person");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/deletePerson/{personId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deletePerson(@PathVariable("personId") Long personId) {
		logger.info("deletePerson::Start");

		Person existingPerson = personService.findById(personId);
		if (existingPerson != null) {
			personService.delete(personId);
			logger.info("deletePerson::personDeleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		} else {
			logger.debug("unable to find person");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/getPerson/{personId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Person> getPerson(@PathVariable("personId") Long personId) {
		logger.info("getPerson::Start");
		Person person = personService.findById(personId);
		if (person != null) {
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else {
			logger.debug("getPerson::person not found with " + personId);
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/findByLastnameIgnoreCase/{lastName}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Person>> findByLastnameIgnoreCase(@PathVariable("lastName") String lastName) {
		logger.info("findByLastnameIgnoreCase::Start");
		List<Person> lstperson = personService.findByLastnameIgnoreCase(lastName);
		if (lstperson != null && lstperson.size()!=0) {
			return new ResponseEntity<List<Person>>(lstperson, HttpStatus.OK);
		} else {
			logger.debug("getPerson::person not found with " + lastName);
			return new ResponseEntity<List<Person>>(lstperson, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/getAllPerson/{pageNumber}/{pageSize}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Page<Person>> getAlPlerson(@PathVariable("pageNumber") Integer pageNumber  , @PathVariable("pageSize") Integer pageSize ) {
		logger.info("getAllPerson::Start");
		Page<Person> personLst = personService.findAll(pageNumber,pageSize);
		if (personLst.getSize()!=0) {
			return new ResponseEntity<Page<Person>>(personLst, HttpStatus.OK);
		} else {
			logger.debug("getAllPerson:: Debug no data fouund");
			return new ResponseEntity<Page<Person>>(personLst, HttpStatus.NO_CONTENT);
		}
	}

}
