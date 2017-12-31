package com.microservices;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.microservices.jpa.repository.PersonRepository;

@SpringBootApplication
public class SpringBootApplicationTest extends SpringBootServletInitializer {
	// implements CommandLineRunner

	@Autowired
	DataSource dataSource;
	@Autowired
	PersonRepository personRepository;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApplicationTest.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationTest.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * 
	 * System.out.println("DATASOURCE = " + dataSource);
	 * 
	 * 
	 * // customerRepository.save(new Customer("newRow","newrowlastname"));
	 * 
	 * // customerRepository.save(new
	 * Customer(4l,"newRowupdated","newrowlastnameupdated"));
	 * 
	 * // customerRepository.delete(new Customer(4l,"fdsfdsf","dfdsfds"));
	 * 
	 * Person person1 = new Person(); person1.setFirstname("hemant");
	 * person1.setLastname("shah"); personRepository.save(person1);
	 * 
	 * System.out.println("\n1.findAll()..."); for (Person person :
	 * personRepository.findAll()) { System.out.println("got it :::"+person); }
	 * 
	 * System.out.println("Done!"+person1);
	 */

	// exit(0);
	// }
}
