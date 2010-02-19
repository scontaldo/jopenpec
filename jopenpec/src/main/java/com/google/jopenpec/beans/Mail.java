package com.google.jopenpec.beans;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static final String TEXT_PLAIN = "text/plain";
	private String subject;
	private String body;
	private String type;
	private String sender;
	private String recipients;
	private MimeMessage message;

	public Mail(String subject, String body, String type, String sender,
			String recipients) throws AddressException,
			MessagingException {
		super();
		this.subject = subject;
		this.body = body;
		this.type = type;
		this.sender = sender;
		this.recipients = recipients;
	}

	public void prepare(Session session) throws AddressException,
			MessagingException {
		message = new MimeMessage(session);
		message.setSender(new InternetAddress(sender));
		message.setSubject(subject);
		message.setContent(body, type);
		if (recipients.indexOf(',') > 0)
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(recipients));
		else
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					recipients));

	}

	public void send() throws MessagingException {
		Transport.send(message);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

}
