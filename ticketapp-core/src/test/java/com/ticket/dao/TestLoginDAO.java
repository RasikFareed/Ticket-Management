package com.ticket.dao;

import com.ticket.exception.PersistenceException;

public class TestLoginDAO {

	public static void main(String[] args) throws PersistenceException {
		
		LoginDAO loginDao=new LoginDAO();
		System.out.println(loginDao.login("rasik", "abc123"));

	}

}
