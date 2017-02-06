package com.ticket.model;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Issue {

	private int id;
	private User userId;
	private Department departmentId;
	private String subject;
	private String description;
	private LocalDateTime dateReported;
	private LocalDateTime dateResolved;
	private String status;
	private String priority;
	
}
