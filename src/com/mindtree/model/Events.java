package com.mindtree.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "EVENTS")
@Table(name = "EVENTS")
public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int EVENT_ID;
	private String EVENT_TITLE;
	private String DESCRIPTION;
	@ManyToMany(mappedBy="events")
	private Collection<Employees> employees = new ArrayList<>();
	
	
	
	public Collection<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(Collection<Employees> employees) {
		this.employees = employees;
	}
	public int getEVENT_ID() {
		return EVENT_ID;
	}
	public void setEVENT_ID(int eVENT_ID) {
		EVENT_ID = eVENT_ID;
	}
	public String getEVENT_TITLE() {
		return EVENT_TITLE;
	}
	public void setEVENT_TITLE(String eVENT_TITLE) {
		EVENT_TITLE = eVENT_TITLE;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
	
}
