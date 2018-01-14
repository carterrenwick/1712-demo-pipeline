package com.revature.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public static void sendRegistrationEmail(String empUsername, String empPassword, String empEmail) {
		final String username = "mydevelopmentemail123@gmail.com";
		final String password = "carter2156";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(empEmail));
			message.setSubject("New Employee");
			message.setText("Hello there!"
				+ "\n\n Username: "+empUsername
				+ "\n Temp Password: "+ empPassword);

			Transport.send(message);

			System.out.println("Employee registration email has been sent!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
