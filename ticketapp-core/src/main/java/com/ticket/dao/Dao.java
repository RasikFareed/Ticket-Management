package com.ticket.dao;

import java.util.List;

import com.ticket.exception.PersistenceException;

public interface Dao<T> {
	
	void save(T t) throws PersistenceException;
	void update(T t);
	void delete(int id);
	
	List<T> findAll();
	T findOne(int id);

}
