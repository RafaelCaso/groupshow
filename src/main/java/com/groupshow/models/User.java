package com.groupshow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;
	
	@Column(nullable=false)
	private UserType userType;
	
	@Column(nullable=false)
	private String email;
	
	@Column(length=100, nullable=false)
	private String firstName;
	
	@Column(length=100, nullable=false)
	private String lastName;
	
	@Column(length=100)
	private String major;
	
	@Column(length=100)
	private String minor;
	
	@Column(length=9)
	private String gradeLevel;

	@Column(length=36, nullable=false)
	private String regTokenID;

	@Column(nullable = false)
	private Boolean isRegTokenActivated;
	
}
