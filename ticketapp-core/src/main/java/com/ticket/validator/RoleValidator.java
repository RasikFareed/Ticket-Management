package com.ticket.validator;

import com.ticket.exception.ValidatorException;
import com.ticket.model.Role;
import com.ticket.util.Validator;

public class RoleValidator {

	Validator validator=new Validator();
	public void save(Role role) throws ValidatorException{
		validator.isInvalid(role.getName(), "NAME");
}
}
