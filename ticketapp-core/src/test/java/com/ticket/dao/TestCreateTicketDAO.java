package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Solution;
import com.ticket.model.User;

public class TestCreateTicketDAO {

	public static void main(String[] args) throws  ServiceException, PersistenceException {

		CreateTicketDAO createTicket=new CreateTicketDAO();
		User user=new User();
		user.setId(3);
		int userId=user.getId();
		user.setEmailId("rasik@gmail.com");
		user.setPassword("abc123");
		
		Issue issue=new Issue();
		issue.setId(12);
		int issueId=issue.getId();
		
		String subject="Finance Failure";
		String description="I could not find my money in bank";
		String department="Finance";
		String priority="High";
		//createTicket.createTicket(user.getEmailId(),user.getPassword(), subject, description, department, priority);
		//createTicket.updateTicket(user.getEmailId(),user.getPassword(), issueId, description);
		//createTicket.updateClose(user.getEmailId(),user.getPassword(),issueId);
	//createTicket.findUserDetails(user.getEmailId(),user.getPassword());
		
		Employee employee=new Employee();
		employee.setId(2);
		employee.setEmailId("xyz@gmail.com");
		employee.setPassword("abc321");
		Solution solution=new Solution();
		SolutionDAO solDao=new SolutionDAO();
		solution.setResolutionDescription("Check you internet connection");
		//createTicket.assignEmployee(employee.getEmailId(), employee.getPassword(), issueId, employee.getId());
		//createTicket.ticketSolution(employee.getEmailId(), employee.getPassword(), issueId, solution.getResolutionDescription());
		//createTicket.findEmployeeTickets(employee.getEmailId(), employee.getPassword());
		//createTicket.deleteTickets(employee.getEmailId(), employee.getPassword(), issueId);
		//IssueDAO issueDao=new IssueDAO();
		//issueDao.findempTickets(employee);
		/*List<Issue> list = issueDao.findempTickets(1);
		Iterator<Issue> i = list.iterator();
		while (i.hasNext()) {
			Issue issues = (Issue) i.next();
			System.out.println(issues.getId()+ "\t" +issues.getSubject() + "\t"
					+ issues.getDescription() +"\t"+issues.getStatus());
		}
*/
	//	issueDao.findempTickets(employee);
		
	/*	List<Solution> list = solDao.findempTickets();
		Iterator<Solution> i = list.iterator();
		while (i.hasNext()) {
			Solution sol = (Solution) i.next();
			System.out.println(list);
		}*/
	}

}
