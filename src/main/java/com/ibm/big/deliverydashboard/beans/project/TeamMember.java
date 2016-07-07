package com.ibm.big.deliverydashboard.beans.project;

import com.ibm.big.deliverydashboard.beans.user.User;

public class TeamMember {

	User person;
	String role;
	
	public User getPerson() {
		return person;
	}
	public void setPerson(User person) {
		this.person = person;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
