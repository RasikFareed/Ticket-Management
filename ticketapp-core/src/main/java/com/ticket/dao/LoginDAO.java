package com.ticket.dao;

import com.ticket.exception.PersistenceException;

public class LoginDAO {

	UserDAO userDao=new UserDAO();
	EmployeeDAO employeeDao=new EmployeeDAO();
	public boolean login(String emailId,String password) throws PersistenceException  {
		
			userDao.findOne(emailId,password).getId();
							return true;

	}
	
	
	public boolean employeeLogin(String emailId,String password) throws PersistenceException  {
		
		employeeDao.findOne(emailId,password).getId();
						return true;
	
	
}

}
