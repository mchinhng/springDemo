package com.example.request;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
	
	@NotNull(message = "id is required")
	private Long id;
	
	@NotBlank(message = "name is required")
	private String name;

	@NotBlank(message = "email is required")
	private String email;

	@NotBlank(message = "gender is required")
	private String gender;

	@NotNull(message = "dob is required")
	private Date dob;

	private boolean DeletedFlg;
}
