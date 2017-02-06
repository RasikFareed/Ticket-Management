package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.ticket.model.Department;
import com.ticket.util.ConnectionUtil;

public class DepartmentDAO implements Dao<Department> {
	private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	
	public void save(Department department) {
	String sql = "INSERT INTO DEPARTMENTS(NAME)VALUES(?)";
	Object[] params = {department.getName()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);
}
	
public void update(Department department) {
	String sql = "UPDATE DEPARTMENTS SET NAME=?,ACTIVE=? WHERE ID=?";
	Object[] params = {department.getName(),department.isActive()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	
public void delete(int id) {
	String sql = "DELETE FROM DEPARTMENTS WHERE ID=?";
	Object params = id;
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	

public List<Department> findAll() {
	String sql = "SELECT ID, NAME,ACTIVE FROM DEPARTMENTS";
	return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

}


public Department findOne(int id) {
	String sql = "SELECT ID, NAME,ACTIVE FROM DEPARTMENTS WHERE ID = ?";
	Object[] params = { id };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));

}

public Department findId(String department) {
	String sql = "SELECT ID FROM DEPARTMENTS WHERE NAME = ?";
	Object[] params = { department };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) ->{ 
		Department departments = new Department();
		departments.setId(rs.getInt("ID"));
		return departments;
	
	});

}
private Department convert(ResultSet rs) throws SQLException {
	Department department = new Department();
	
	department.setId(rs.getInt("ID"));
	department.setName(rs.getString("NAME"));
	department.setActive(rs.getBoolean("ACTIVE"));
	
	return department;
}

}
