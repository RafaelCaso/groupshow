package com.groupshow.utilities;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class Registrar {
	
	public void sendEmail() throws IOException {
	
		
	Email from = new Email("groupshow18@gmail.com");
	String subject = "Registration Link for";
	Email to = new Email("msatin2009@gmail.com");
	
	Content content = new Content("text/plain", "Test 2 for environment variable");
	
	Mail mail = new Mail(from, subject, to, content);
	
	SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	System.out.println(System.getenv("SENDGRID_API_KEY"));
	
	
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
