package org.jboss.as.quickstarts.jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.InvalidDestinationException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/message-queue")
})
public class MessageBean implements MessageListener {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	@Override
	public void onMessage(final Message message) {
		
		Connection connection = null;
		
		try {
			
			System.out.println( "#### Message " + ((TextMessage) message).getText());
			
			connection = factory.createConnection();
			connection.createSession(false, Session.AUTO_ACKNOWLEDGE).createProducer(message.getJMSReplyTo()).send(message);
		
		} catch (InvalidDestinationException e) {
			System.out.println("Dropping invalid message" + e.getMessage());
		
		} catch (Exception e) {
			throw new RuntimeException("Could not reply to message", e);
		
		} finally {
			
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) { }
			}
		}

	}
}
