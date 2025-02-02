package com.example.entity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.example.request.CreateUserRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "muser", schema="public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "DoB")
	private Date dob;
	
	@Column(name = "DeleletedFlg")
	private Boolean DeleletedFlg;
	
	@Transient
	private String firstName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	@NotNull
	private Address address;
	
	@OneToMany(mappedBy = "user")
	private List<Subject> learningSubjects;
	
	public User(CreateUserRequest createUserRequest) {
		this.name = createUserRequest.getName();
		this.email = createUserRequest.getEmail();
		this.gender = createUserRequest.getGender();
		this.dob = createUserRequest.getDob();
		this.DeleletedFlg = createUserRequest.isDeletedFlg();
		this.firstName = createUserRequest.getName() + " " + createUserRequest.getEmail();
	}
}
