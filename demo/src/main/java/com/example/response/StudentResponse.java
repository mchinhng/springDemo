package com.example.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
// ==
//@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

//	@JsonIgnore
	private long id;
	
//	@JsonProperty("first_name")
	private String firstName;
	
	private String lastName;
}
