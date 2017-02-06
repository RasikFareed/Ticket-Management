package com.ticket.validator;

import com.ticket.exception.ValidatorException;
import com.ticket.model.Department;
import com.ticket.util.Validator;

public class DepartmentValidator {

	Validator validator=new Validator();
	public void save(Department department) throws ValidatorException{
		validator.isInvalid(department.getName(), "NAME");
}
}
