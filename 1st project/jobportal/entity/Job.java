package com.example.jobportal.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Component
public class Job {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int jobId;
	private String designation;
	private String jobRole;
	private String location;
	private double ctc;
	private boolean openStatus;
	
	
	public boolean getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(boolean openStatus) {
		this.openStatus = openStatus;
	}

	@ManyToOne
	private Company compMap;
	
	@ManyToMany
	private List<Skill> skillList;

	public int getJobId() {
		return jobId;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCtc() {
		return ctc;
	}

	public void setCtc(double ctc) {
		this.ctc = ctc;
	}

	public Company getCompMap() {
		return compMap;
	}

	public void setCompMap(Company compMap) {
		this.compMap = compMap;
	}

	
	
}
