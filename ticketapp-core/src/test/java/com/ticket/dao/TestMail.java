package com.ticket.dao;

import com.ticket.util.MailUtil;

public class TestMail {

	public static void main(String[] args) {
		try {
			String emailId = "muhammed.rasik.795@gmail.com";
			int issueId = 0;
			MailUtil.sendSimpleMail(emailId,"Ticket Created Sucessfully.Your Ticket id is:",issueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
