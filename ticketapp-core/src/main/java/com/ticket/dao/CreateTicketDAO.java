package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.exception.PersistenceException;
import com.ticket.exception.ValidatorException;
import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Role;
import com.ticket.model.Solution;
import com.ticket.model.User;
import com.ticket.util.MailUtil;

public class CreateTicketDAO {
	Issue issue = new Issue();
	IssueDAO issueDao = new IssueDAO();

	public void registration(String name, String emailId, String password) throws PersistenceException {
		User user = new User();
		UserDAO userDao = new UserDAO();

		user.setName(name);
		user.setEmailId(emailId);
		user.setPassword(password);

		userDao.save(user);
	}

	public void createTicket(String emailId, String password, String subject, String description, String department,
			String priority) throws PersistenceException {

		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.login(emailId, password)) {

				User user = new User();
				UserDAO userDao = new UserDAO();

				int userId = userDao.findUserId(emailId).getId();
				user.setId(userId);
				issue.setUserId(user);

				issue.setSubject(subject);
				issue.setDescription(description);

				Department departments = new Department();
				DepartmentDAO departmentDao = new DepartmentDAO();
				int departmentId = departmentDao.findId(department).getId();
				departments.setId(departmentId);
				issue.setDepartmentId(departments);
				issue.setPriority(priority);
				issueDao.save(issue);

				int issueId = issueDao.findIssueId(userId, subject, description).getId();

				Solution solution = new Solution();
				SolutionDAO solutionDao = new SolutionDAO();
				issue.setId(issueId);
				solution.setIssueId(issue);

				Role role = new Role();
				role.setId(2);

				Employee employee = new Employee();
				EmployeeDAO employeeDao = new EmployeeDAO();
				int employeeId = employeeDao.findEmployeeId(departmentId, role.getId()).getId();
				employee.setId(employeeId);
				String employeeEmail=employeeDao.findEmployeeEmailId(employeeId).getEmailId();
				solution.setEmployeeId(employee);
				solutionDao.save(solution);
				try {
					MailUtil.sendSimpleMail(emailId,"Ticket Created Sucessfully.Your Ticket id is:",issueId);
					MailUtil.sendSimpleMail(employeeEmail,"A ticket has been created. The issue id is:",issueId);
				} catch (Exception e) {

				}
			}

		} catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}

	}

	public void updateTicket(String emailId, String password, int issueId, String updateDescription)throws PersistenceException {

		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.login(emailId, password)) {
				User user = new User();
				UserDAO userDao = new UserDAO();

				int userId = userDao.findUserId(emailId).getId();
				user.setId(userId);
				issue.setUserId(user);

				if ("Closed".equals(issueDao.findStatus(userId, issueId).getStatus())
						|| "CLOSED".equals(issueDao.findStatus(userId, issueId).getStatus())) {

					System.out.println("You cant update now!");
				} else {

					issue.setUserId(user);

					issue.setId(issueId);
					issue.setStatus("Inprogress");
					issue.setDescription(updateDescription);

					issueDao.updateDescription(issue);
				}
			}
		}

		catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}
	}

	public void updateClose(String emailId, String password, int issueId) throws PersistenceException {

		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.login(emailId, password)) {

				User user = new User();
				UserDAO userDao = new UserDAO();

				int userId = userDao.findUserId(emailId).getId();
				user.setId(userId);
				issue.setUserId(user);

				issue.setId(issueId);

				issueDao.updateClose(issue);
			}
		} catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}
	}

	
	public List<Issue> findUserDetails(Issue issue) throws PersistenceException{
		return issueDao.findUserDetails(issue);
	}
	
	
	
	
	
	
/*	public void findUserDetails(String emailId, String password) throws PersistenceException {
		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.login(emailId, password)) {
				
				User user = new User();
				UserDAO userDao = new UserDAO();

				int userId = userDao.findUserId(emailId).getId();
				user.setId(userId);
				issueDao.findUserDetails(user.getId());

				return;
			}
		} catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}

	}*/

	public void assignEmployee(String emailId, String password, int issueId, int employeeId)throws PersistenceException {
		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.employeeLogin(emailId, password)) {
				Employee employee = new Employee();
				employee.setEmailId(emailId);
				employee.setPassword(password);
				EmployeeDAO employeeDao = new EmployeeDAO();
				int currentEmployeeDepartmentId = employeeDao.findEmployeeDepartmentId(emailId, password)
						.getDepartmentId().getId();
				int givenEmployeeDepartmentId = employeeDao.findDepartmentId(employeeId).getDepartmentId().getId();

				if (currentEmployeeDepartmentId == givenEmployeeDepartmentId) {

					Solution solution = new Solution();
					SolutionDAO solutionDao = new SolutionDAO();

					issue.setId(issueId);
					solution.setIssueId(issue);

					employee.setId(employeeId);
					solution.setEmployeeId(employee);

					solutionDao.updateEmployeeId(solution);

					issueDao.updateStatus(issue);
				} else {
					System.out.println("Department dosent match");
				}

			}
		} catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}

	}

	public void ticketSolution(String emailId, String password, int issueId, String ticketSolution)throws PersistenceException {
		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.employeeLogin(emailId, password)) {
				Employee employee=new Employee();
				employee.setEmailId(emailId);
				employee.setPassword(password);
				EmployeeDAO employeeDao = new EmployeeDAO();

				Solution solution = new Solution();
				SolutionDAO solutionDao = new SolutionDAO();

				if(employeeDao.findOne(emailId, password).getId()==solutionDao.findEmployeeId(issueId).getEmployeeId().getId()){
		
				
				issue.setId(issueId);
				solution.setIssueId(issue);
				solution.setResolutionDescription(ticketSolution);

				solutionDao.updateSolution(solution);

				issueDao.updateSolutionStatus(issue);
				}
				else{
					System.out.println("You are not assigned to this issue");
				}
				try {
					MailUtil.sendSimpleMail(emailId,"The Solution for your query is as follows:"+ticketSolution+"-"+"Your ticket id is:",issueId);
				} catch (Exception e) {

				}
				
			}
		} catch (PersistenceException e) {
			throw new PersistenceException("Login Failed", e);
		}

	}

	public void deleteTickets(String emailId, String password, int issueId) throws PersistenceException {
		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.employeeLogin(emailId, password)) {
				Employee employee=new Employee();
				EmployeeDAO employeeDao=new EmployeeDAO();
				employee.setEmailId(emailId);
				employee.setPassword(password);
				int employeeRoleId=employeeDao.findEmployeeRoleId(emailId, password).getRoleId().getId();
				
				Role role=new Role();
				role.setName("Admin");
				RoleDAO roleDao=new RoleDAO();
				int adminRoleId=roleDao.findRoleId(role).getId();

				if(employeeRoleId==adminRoleId){
					SolutionDAO solutionDao=new SolutionDAO();
					solutionDao.delete(issueId);
					issueDao.delete(issueId);
				}
				else{
					System.out.println("You dont have enough rights to delete");
				}
				
				
			}

	}catch (PersistenceException e) {
		throw new PersistenceException("Login Failed", e);
	}

}

	
	public List<Issue> findEmployeeTickets(String emailId, String password) throws PersistenceException{
		
			Employee employee=new Employee();
			EmployeeDAO employeeDao=new EmployeeDAO();
			employee.setEmailId(emailId);
			employee.setPassword(password);
			int employeeId=employeeDao.findOne(emailId, password).getId();
			return issueDao.findempTickets(employeeId);

	}
	
/*	public void findEmployeeTickets(String emailId, String password) throws PersistenceException{
		LoginDAO loginDao = new LoginDAO();
		try {
			if (loginDao.employeeLogin(emailId, password)) {
				Employee employee=new Employee();
				EmployeeDAO employeeDao=new EmployeeDAO();
				employee.setEmailId(emailId);
				employee.setPassword(password);
				int employeeId=employeeDao.findOne(emailId, password).getId();
				
				issueDao.findempTickets(employeeId);
				List<Issue> list = issueDao.findempTickets(employeeId);
				Iterator<Issue> i = list.iterator();
				while (i.hasNext()) {
					Issue issues = (Issue) i.next();
					System.out.println(issues.getId()+ "\t" +issues.getSubject() + "\t"
							+ issues.getDescription() +"\t"+issues.getStatus());
				}
				
			}
		
	}catch (PersistenceException e) {
		throw new PersistenceException("Login Failed", e);
	}
		
}*/
	
}
