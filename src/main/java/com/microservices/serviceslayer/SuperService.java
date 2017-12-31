package com.microservices.serviceslayer;

import java.io.Serializable;
import java.util.List;

public interface SuperService<E> {

	public E save(E entity);

	public void delete(Serializable id);

	public List<E> findAll();

	public E findById(Serializable id);

}
