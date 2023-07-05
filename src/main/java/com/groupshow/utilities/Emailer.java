package com.groupshow.utilities;

import java.io.IOException;

import com.groupshow.user.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import io.github.cdimascio.dotenv.Dotenv;

public class Emailer {
	private final static String frontendUrlBase = "http://localhost:5173";
	private final static Dotenv dotenv = Dotenv.load();
	private final static SendGrid sendGrid = new SendGrid(dotenv.get("SENDGRID_API_KEY"));

	public static void sendRegistrationEmail(User user) throws IOException {
		var senderEmail = new Email("groupshow18@gmail.com");
		var recipientEmail = new Email(user.getEmail());
		String emailSubjectLine = "GroupShow Registration Link for " + user.getFirstName() + " " + user.getLastName();
		var emailBody = new Content("text/plain", "Congratulations! " +
				"Your school has registered you for Group Show!\n\n" +
				"Your temporary password is " + user.getPassword() + "\n\n" +
				"Please follow the link below to activate your account:\n\n" +
				frontendUrlBase + "/auth/activate-account/" + user.getUserID() + "/" + user.getRegistrationToken());

		try {
			sendSGEmail(senderEmail, emailSubjectLine, recipientEmail, emailBody);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void sendPasswordResetEmail(String userEmail) throws IOException {
		var senderEmail = new Email("groupshow18@gmail.com");
		var recipientEmail = new Email(userEmail);
		String emailSubjectLine = "Reset Your Group Show Password";
		var emailBody = new Content("text/plain",
				"Please follow the link below to reset your Group Show password:\n\n" +
				frontendUrlBase + "/auth/reset-password");

		sendSGEmail(senderEmail, emailSubjectLine, recipientEmail, emailBody);
	}

	private static void sendSGEmail(Email senderEmail, String emailSubjectLine, Email recipientEmail, Content emailBody) throws IOException {
		var mailObject = new Mail(senderEmail, emailSubjectLine, recipientEmail, emailBody);
		var activationRequest = new Request();

		activationRequest.setMethod(Method.POST);
		activationRequest.setEndpoint("mail/send");
		activationRequest.setBody(mailObject.build());

		sendGrid.api(activationRequest);
	}
}
