package com.ticket.model;

import lombok.Data;

@Data
public class User {

	private int id;
	private String name;
	private String emailId;
	private String password;
	private boolean active;
}
