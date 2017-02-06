package com.ticket.validator;

import com.ticket.exception.ValidatorException;
import com.ticket.model.Employee;
import com.ticket.util.Validator;

public class EmployeeValidator {

	Validator validator=new Validator();
	public void save(Employee employee) throws ValidatorException{
		validator.isInvalid(employee.getName(), "NAME");
		validator.isInvalid(employee.getEmailId(),"EMAIL_ID");
		validator.isInvalid(employee.getPassword(),"PASSWORD");
}

}
