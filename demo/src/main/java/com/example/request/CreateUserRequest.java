package com.example.request;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {
	@JsonProperty("full_name")
	@NotBlank(message = "name is required")
	private String name;

	@NotBlank(message = "email is required")
	private String email;

	@NotBlank(message = "gender is required")
	private String gender;

	@NotNull(message = "dob is required")
	private Date dob;

	private boolean DeletedFlg;

	private String street;
	private String city;
	private List<CreateSubjectRequest> subjectsLearning;
}