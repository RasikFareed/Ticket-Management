package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.model.User;

public class TestUserDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user=new User();
		
		user.setName("Rasik");
		/*user.setEmailId("rasik@gmail.com");
		user.setPassword("abc123");*/
		
		
		
		UserDAO userDao=new UserDAO();
		//userDao.save(user);
		//userDao.update(user);
		
	/*List<User> list = userDao.findAll();
		Iterator<User> i = list.iterator();
		while (i.hasNext()) {
			User users = (User) i.next();
			System.out.println(users.getId()+ "\t" +users.getName() + "\t"
					+ users.getEmailId() + "\t" + users.isActive());
		}
		
		user = userDao.findOne("rasik@gmail.com");
		System.out.println(user.getPassword());*/
	}

}
