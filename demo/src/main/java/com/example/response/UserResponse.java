package com.example.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Subject;
import com.example.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

	@JsonProperty("full_name")
	private String name;

	private String email;

	private String gender;

	private Date dob;

	private boolean DeletedFlg;
	
	private String firstName;
	
	private String street;
	private String city;
	private List<SubjectResponse> learningSubjects;
	
	public UserResponse (User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.dob = user.getDob();
		this.DeletedFlg = user.getDeleletedFlg();
		this.firstName = user.getFirstName() + " " + user.getEmail();
		this.street = user.getAddress().getStreet();
		this.city = user.getAddress().getCity();
		
		if(user.getLearningSubjects() != null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for(Subject subject: user.getLearningSubjects()) {
				learningSubjects.add(new SubjectResponse(subject));
			}
		}
	}
}
