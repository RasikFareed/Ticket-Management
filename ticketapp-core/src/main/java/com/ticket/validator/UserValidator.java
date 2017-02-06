package com.ticket.validator;

import com.ticket.exception.ValidatorException;
import com.ticket.model.User;
import com.ticket.util.Validator;

public class UserValidator {
	Validator validator=new Validator();
	public void save(User user) throws ValidatorException{
		validator.isInvalid(user.getName(), "NAME");
		validator.isInvalid(user.getEmailId(),"EMAIL_ID");
		validator.isInvalid(user.getPassword(), "PASSWORD");

}
}