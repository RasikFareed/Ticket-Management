package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;


public class TestIssueDAO {

	public static void main(String[] args) {
		Issue issue=new Issue();
		IssueDAO issueDao=new IssueDAO();
		User user=new User();
		user.setId(2);
		issue.setUserId(user);
		
		Department department=new Department();
		department.setId(2);
		issue.setDepartmentId(department);
		
		issue.setSubject("asasasa");
		issue.setDescription("baklalaa");
		issue.setPriority("high");
		
		//issueDao.save(issue);
		/*List<Issue> list = issueDao.findAll();
		Iterator<Issue> i = list.iterator();
		while (i.hasNext()) {
			Issue issues = (Issue) i.next();
			System.out.println(issues.getId()+"\t"+issues.getUserId().getId()+"\t"+issues.getDepartmentId().getId()
					+"\t"+issues.getSubject()+"\t"+issues.getDescription()+"\t"+issues.getDateReported()+"\t"
					+issues.getDateResolved()+"\t"+issues.getStatus());
		}*/
		System.out.println(issueDao.findStatus(2, 3).getStatus());
	}

}
