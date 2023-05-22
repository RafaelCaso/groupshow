package com.groupshow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userID;
	
	@Enumerated(EnumType.STRING)
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

	@Column(name = "reg_token_id", length=36, nullable=false)
	private String regTokenID;

	@Column(nullable = false)
	private Boolean isRegTokenActivated;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Painting> paintings;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Performance> performances;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Photograph> photographs;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Song> songs;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Video> videos;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Writing> writings;


}
