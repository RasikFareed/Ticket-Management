package com.ticket.validator;

import com.ticket.exception.ValidatorException;
import com.ticket.util.Validator;

public class CreateTicketValidator {

	Validator validator=new Validator();
	public void registration(String name,String emailId,String password) throws ValidatorException{
		validator.isInvalid(name, "NAME");
		validator.isInvalid(emailId, "EMAIL_ID");
		validator.isInvalid(password, "PASSWORD");
	}
	public void createTicket(String emailId,String password,String subject,String description,String department,String priority) throws ValidatorException{
	
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(subject, "SUBJECT");
		validator.isInvalid(description, "DESCRIPTION");
		validator.isInvalid(department, "DEPARTMENT");
		validator.isInvalid(priority, "PRIORITY");
	}
	public void updateTicket(String emailId,String password,int issueId,String updateDescription) throws ValidatorException{
		
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(issueId, "ISSUE_ID");
		validator.isInvalid(updateDescription, "DESCRIPTION");
	}
	
	public void updateClose(String emailId,String password,int issueId) throws ValidatorException{
		
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(issueId, "ISSUE_ID");
		
	}
	
	public void findUserDetails(String emailId,String password) throws ValidatorException{
		
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		
	}
	public void assignEmployee(String emailId, String password, int issueId, int employeeId) throws ValidatorException{
		
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(issueId, "ISSUE_ID");
		validator.isInvalid(employeeId, "EMPLOYEE_ID");
	}
	
	public void ticketSolution(String emailId, String password, int issueId, String ticketSolution) throws ValidatorException{
		
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(issueId, "ISSUE_ID");
		validator.isInvalid(ticketSolution, "SOLUTION");
	}
	public void findEmployeeTickets(String emailId, String password) throws ValidatorException{
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
	}
	public void deleteTickets(String emailId, String password, int issueId) throws ValidatorException{
		validator.isInvalid(emailId, "EMAIL");
		validator.isInvalid(password, "PASSWORD");
		validator.isInvalid(issueId, "ISSUE_ID");
	}
}
