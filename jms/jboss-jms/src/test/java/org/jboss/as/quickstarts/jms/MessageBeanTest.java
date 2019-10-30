/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.jms.MessageBean;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MessageBeanTest {
	
	private final Logger logger = Logger.getLogger(getClass());

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
						 .addClass(MessageBean.class)
						 .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
	}

	@Resource(mappedName = "jms/queue/message-queue", name = "jms/queue/message-queue")
	private Destination testQueue;

	@Resource(mappedName = "/ConnectionFactory")
	private ConnectionFactory factory;

	private static final long QUALITY_OF_SERVICE_THRESHOLD_MS = 5 * 60 * 1000;

	@Test
	public void jms() throws Exception {
		
		logger.info( "#### Init Tests ... ");
		
		String messageBody = "ping";

		Connection connection = null;
		Session session = null;
		
		try {
			
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			TemporaryQueue tempQueue = session.createTemporaryQueue();
			MessageProducer producer = session.createProducer(testQueue);
			MessageConsumer consumer = session.createConsumer(tempQueue);

			connection.start();

			Message request = session.createTextMessage(messageBody);
			request.setJMSReplyTo(tempQueue);

			producer.send(request);
			Message response = consumer.receive(QUALITY_OF_SERVICE_THRESHOLD_MS);
			Assert.assertNotNull(response);
			String responseBody = ((TextMessage) response).getText();

			Assert.assertEquals("Should have responded with same message", messageBody, responseBody);
			
		} finally {
			
			if (connection != null) {
				connection.close();
			}
			
			if(session != null) {
			session.close();
			}
		}

	}
}
