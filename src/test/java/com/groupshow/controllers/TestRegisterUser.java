package com.groupshow.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.User;
import com.groupshow.models.UserType;

@SpringBootTest
public class TestRegisterUser {

	@Autowired
	private UserController userController;
	
	@Test
	public void testRegisterUser() {
		User user = new User();
		
		user.setFirstName("TestfName");
		user.setLastName("TestlName");
		user.setEmail("test@testemail.com");
		user.setUserType(UserType.STUDENT);
		user.setGradeLevel("TestLevel");
		user.setMajor("TestMajor");
		user.setMinor("TestMinor");
		
		User savedUser = userController.addUser(user);
		
		
		assertNotNull(savedUser.getUserID());
		assertEquals(user.getFirstName(), savedUser.getFirstName());
		assertNotNull(savedUser.getFirstName());
		assertEquals(user.getLastName(), savedUser.getLastName());
		assertNotNull(savedUser.getLastName());
		assertEquals(user.getEmail(), savedUser.getEmail());
		assertNotNull(savedUser.getEmail());
		assertEquals(user.getUserType(), savedUser.getUserType());
		assertNotNull(savedUser.getUserType());
		assertEquals(user.getGradeLevel(), savedUser.getGradeLevel());
		assertEquals(user.getMajor(), savedUser.getMajor());
		assertEquals(user.getMinor(), savedUser.getMinor());
	}
}
