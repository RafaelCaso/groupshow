package com.groupshow;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.groupshow.utilities.Registrar;

@SpringBootApplication
public class GroupShowAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupShowAppApplication.class, args);
		
//		Registrar registrar = new Registrar();
//		try {
//			registrar.sendEmail();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
