package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.User;
import com.ticket.util.ConnectionUtil;

public class UserDAO implements Dao<User> {

	private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	@Override
	public void save(User u) throws PersistenceException {
		try{
	String sql = "INSERT INTO USERS(NAME,EMAIL_ID,PASSWORD)VALUES(?,?,?)";
	Object[] params = {u.getName(),u.getEmailId(),u.getPassword()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);
		}
		catch(DuplicateKeyException e){
			throw new PersistenceException("EMAIL_ID already registered",e);
		}
}
	@Override
public void update(User u) {
	String sql = "UPDATE USERS SET NAME=?,EMAIL_ID=?,PASSWORD=?,ACTIVE=? WHERE ID=?";
	Object[] params = {u.getName(),u.getEmailId(),u.getPassword(),u.isActive(),u.getId()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	@Override
public void delete(int id) {
	String sql = "DELETE FROM USERS WHERE ID=?";
	Object params = id;
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	
@Override
public List<User> findAll() {
	String sql = "SELECT ID, NAME,EMAIL_ID,PASSWORD,ACTIVE FROM USERS";
	return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

}

@Override
public User findOne(int id) {
	String sql = "SELECT ID, NAME,EMAIL_ID,ACTIVE FROM USERS WHERE ID = ?";
	Object[] params = { id };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));

}

public User findOne(String emailId,String password) throws PersistenceException {
	try{
	String sql = "SELECT ID FROM USERS WHERE EMAIL_ID=? AND PASSWORD=?";
	Object[] params = { emailId,password };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
		User user=new User();
		user.setId(rs.getInt("ID"));
		return user;
	
	});
	}
	catch(EmptyResultDataAccessException e){
		throw new PersistenceException("Wrong Email id or Password",e);
	}
}

public User findUserId(String emailId) {
	String sql = "SELECT ID FROM USERS WHERE EMAIL_ID = ?";
	Object[] params = { emailId };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
		User user=new User();
		user.setId(rs.getInt("ID"));
		return user;
	
	});

}

private User convert(ResultSet rs) throws SQLException {
	User user = new User();
	
	user.setId(rs.getInt("ID"));
	user.setName(rs.getString("NAME"));
	user.setEmailId(rs.getString("EMAIL_ID"));
	user.setActive(rs.getBoolean("ACTIVE"));
	return user;
}

}
