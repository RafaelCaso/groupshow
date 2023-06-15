package com.groupshow.utilities;

import java.io.IOException;

import com.groupshow.user.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import io.github.cdimascio.dotenv.Dotenv;

public class Emailer {
	
	public static Response sendRegistrationEmail(User user) throws IOException {
		var senderEmail = new Email("groupshow18@gmail.com");
		var recipientEmail = new Email(user.getEmail());
		String emailSubjectLine = "GroupShow Registration Link for " + user.getFirstName() + " " + user.getLastName();
		var emailBody = new Content("text/plain", "Congratulations! " +
				"You have been registered for Group Show.\n\n" +
				"Your temporary password is " + user.getPassword() + "\n\n" +
				"Please follow the link below to activate your account:\n\n" +
				"http://localhost:8000/auth/activate?userID=" + user.getUserID() + "&regToken=" + user.getRegistrationToken());

		var mailObject = new Mail(senderEmail, emailSubjectLine, recipientEmail, emailBody);

		var dotenv = Dotenv.load();
		var sendGrid = new SendGrid(dotenv.get("SENDGRID_API_KEY"));

		var activationRequest = new Request();

		try {
			activationRequest.setMethod(Method.POST);
			activationRequest.setEndpoint("mail/send");
			activationRequest.setBody(mailObject.build());

			return sendGrid.api(activationRequest);
		} catch (IOException e) {
			throw e;
		}
	}
}
