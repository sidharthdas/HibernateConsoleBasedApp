package com.mindtree.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "EMPLOYEES")
@Table(name = "EMPLOYEES")
public class Employees {

	@Id
	@Column(nullable = false)
	private String MID;
	private String NAME;
	private String JOIN_DATE;
	private String EMAIL_ID;
	@ManyToMany
	private Collection<Events> events = new ArrayList<>();

	public Collection<Events> getEvents() {
		return events;
	}

	public void setEvents(Collection<Events> events) {
		this.events = events;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getJOIN_DATE() {
		return JOIN_DATE;
	}

	public void setJOIN_DATE(String jOIN_DATE) {
		JOIN_DATE = jOIN_DATE;
	}

	public String getEMAIL_ID() {
		return EMAIL_ID;
	}

	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}

}
