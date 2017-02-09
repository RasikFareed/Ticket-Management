package com.ticket.service;

import java.util.List;

import com.ticket.dao.CreateTicketDAO;
import com.ticket.dao.LoginDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidatorException;
import com.ticket.model.Issue;
import com.ticket.validator.CreateTicketValidator;

public class CreateTicketService {

	CreateTicketValidator createTicketValidator = new CreateTicketValidator();
	CreateTicketDAO createTicketDao = new CreateTicketDAO();
	LoginDAO loginDao=new LoginDAO();

	public void registration(String name, String emailId, String password) throws ServiceException {

		try {
			createTicketValidator.registration(name, emailId, password);
			createTicketDao.registration(name, emailId, password);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Registration Failed", e);
		}
	}
	
	public void login(String emailId, String password) throws ServiceException {

		try {
			createTicketValidator.login(emailId, password);
			loginDao.login(emailId, password);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Login Failed", e);
		}
	}

	public void createTicket(String emailId, String password, String subject, String description, String department,
			String priority) throws ServiceException {

		try {
			createTicketValidator.createTicket(emailId, password, subject, description, department, priority);
			createTicketDao.createTicket(emailId, password, subject, description, department, priority);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Create Ticket", e);

		}
	}

	public void updateTicket(String emailId, String password, int issueId, String updateDescription)
			throws ServiceException {

		try {
			createTicketValidator.updateTicket(emailId, password, issueId, updateDescription);
			createTicketDao.updateTicket(emailId, password, issueId, updateDescription);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Update Ticket", e);

		}
	}

	public void updateClose(String emailId, String password, int issueId) throws ServiceException {

		try {
			createTicketValidator.updateClose(emailId, password, issueId);
			createTicketDao.updateClose(emailId, password, issueId);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Close Ticket", e);

		}
	}
	
	
	
	public List<Issue> findUserDetails(Issue issue) throws ServiceException{
		try {
			return createTicketDao.findUserDetails(issue);
		} catch (PersistenceException e) {
			throw new ServiceException("Cannot View Ticket", e);
		} 
	}
	
	
	
	
	

/*	public void findUserDetails(String emailId, String password) throws ServiceException {

		try {
			createTicketValidator.findUserDetails(emailId, password);
			createTicketDao.findUserDetails(emailId, password);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot View Ticket", e);

		}
	}*/

	public void assignEmployee(String emailId, String password, int issueId, int employeeId) throws ServiceException {

		try {
			createTicketValidator.assignEmployee(emailId, password, issueId, employeeId);
			createTicketDao.assignEmployee(emailId, password, issueId, employeeId);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Assign Ticket", e);

		}
	}

	public void ticketSolution(String emailId, String password, int issueId, String ticketSolution)
			throws ServiceException {

		try {
			createTicketValidator.ticketSolution(emailId, password, issueId, ticketSolution);
			createTicketDao.ticketSolution(emailId, password, issueId, ticketSolution);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Create Ticket Solution", e);

		}
	}
	
	

/*	public void findEmployeeTickets(String emailId, String password) throws ServiceException{
		
		try {
			createTicketValidator.findEmployeeTickets(emailId,password);
			createTicketDao.findEmployeeTickets(emailId,password);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot View Tickets", e);

		}
		
	}*/
	
	public List<Issue> findEmployeeTickets(String emailId,String password) throws ServiceException{
		try {
			return createTicketDao.findEmployeeTickets(emailId,password);
		} catch (PersistenceException e) {
			throw new ServiceException("Cannot View Ticket", e);
		} 
	}
	
	
	public void deleteTickets(String emailId, String password, int issueId) throws ServiceException{
		
		try {
			createTicketValidator.deleteTickets(emailId, password, issueId);
			createTicketDao.deleteTickets(emailId, password, issueId);
		} catch (ValidatorException | PersistenceException e) {
			throw new ServiceException("Cannot Delete Ticket", e);

		}
		
	}

}
