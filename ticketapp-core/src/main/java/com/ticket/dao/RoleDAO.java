package com.ticket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Role;
import com.ticket.util.ConnectionUtil;

public class RoleDAO {
	private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	public Role findRoleId(Role role) {
		String sql = "SELECT ID FROM ROLES WHERE NAME = ? AND ACTIVE=1";
		Object[] params = {role.getName()};
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Role roles=new Role();
			roles.setId(rs.getInt("ID"));
			return roles;
		});
	}

}
