package com.groupshow.utilities;

import java.io.IOException;

import com.groupshow.models.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import io.github.cdimascio.dotenv.Dotenv;

public class Registrar {
	
	public static void sendEmail(User user) throws IOException {
		Email sender = new Email("groupshow18@gmail.com");
		Email recipient = new Email(user.getEmail());
		String emailSubject = "Registration Link for " + user.getFirstName() + " " + user.getLastName();
		Content emailBody = new Content("text/plain", "Congratulations! " +
				"You have been registered for Group Show.\n\nPlease click the following " +
				"link to activate your account:\n\n" +
				"http://localhost:8000/user/activate?regTokenID=" + user.getRegTokenID() + "."
				);

		Mail emailMessage = new Mail(sender, emailSubject, recipient, emailBody);

		Dotenv dotenv = Dotenv.load();
		String sendgridApiKey = dotenv.get("SENDGRID_API_KEY");
		SendGrid sg = new SendGrid(sendgridApiKey);

		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(emailMessage.build());
			Response response = sg.api(request);

		} catch (IOException e) {
			throw e;
		}

	}
}
