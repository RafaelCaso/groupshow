package com.groupshow.utilities;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import io.github.cdimascio.dotenv.Dotenv;

public class Registrar {
	
	public void sendEmail() throws IOException {
	
		
	Email from = new Email("groupshow18@gmail.com");
	String subject = "Registration Link for";
	Email to = new Email("aracaso@outlook.com");
	
	Content content = new Content("text/plain", "Test 2 for environment variable");
	
	Mail mail = new Mail(from, subject, to, content);
	
	Dotenv dotenv = Dotenv.load();
	String apiKey = dotenv.get("SENDGRID_API_KEY");
	
	SendGrid sg = new SendGrid(apiKey);
	
	
	Request request = new Request();
	
	try {
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		
		Response response = sg.api(request);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		
	} catch (IOException e) {
		throw e;
	}
	
	}
	
}
