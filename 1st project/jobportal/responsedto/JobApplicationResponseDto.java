package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.Applicationstatus;


@Component
public class JobApplicationResponseDto {
	
	
	private int applicationId;
	private LocalDate appliedDate;
	private Applicationstatus status;
	private float skillMatching;
	private boolean locationMatching;
	private HashMap<String, String> options;
	


	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Applicationstatus getStatus() {
		return status;
	}

	public void setStatus(Applicationstatus status) {
		this.status = status;
	}

	public float getSkillMatching() {
		return skillMatching;
	}

	public void setSkillMatching(float skillMatching) {
		this.skillMatching = skillMatching;
	}

	public boolean isLocationMatching() {
		return locationMatching;
	}

	public void setLocationMatching(boolean locationMatching) {
		this.locationMatching = locationMatching;
	}

	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}



	
	

	

}
