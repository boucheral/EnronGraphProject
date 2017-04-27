package com.ab.obj;

import java.util.Objects;

public class Person{
	
	String emailAddress;
	
	public Person(){
		
	}
	public Person(String email){
		emailAddress = email;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String toString() {
		return emailAddress;
	}
	public boolean equals(Person p) {
		if (p.getEmailAddress().equals(this.emailAddress))
				return true;
		else
			return false;
	}
	
}
