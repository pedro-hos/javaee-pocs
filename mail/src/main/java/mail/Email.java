package mail;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@SessionScoped
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:jboss/mail/OutroMail")
	private Session mySession;

	public void send(final String from, final String to, final String subject, final String content) throws Exception {
		Message message = new MimeMessage(mySession);
		message.setFrom(new InternetAddress(from));
		Address toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		message.setSubject(subject);
		message.setContent(content, "text/plain");
		Transport.send(message);
	}

}
