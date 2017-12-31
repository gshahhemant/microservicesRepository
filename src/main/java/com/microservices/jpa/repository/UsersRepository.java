package com.microservices.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.model.Users;

public interface UsersRepository extends CrudRepository<Users, String> {

}
