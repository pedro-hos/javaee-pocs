package br.redhat.jmx.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ClientCommunicatorJboss7 {

	private static final Logger log = Logger.getLogger(ClientCommunicatorJboss7.class.getName());

	private static final String IP_EAP = "127.0.0.1";
	private static final String PORT_EAP = "9990";

	public void pingServer() {

		String url = "service:jmx:remote+http://" + IP_EAP + ":" + PORT_EAP;
		log.info("Connecting to " + url);

		JMXServiceURL serviceURL = null;
		JMXConnector jmxConnector = null;
		MBeanServerConnection mServer = null;

		try {

			serviceURL = new JMXServiceURL(url);
			jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
			mServer = jmxConnector.getMBeanServerConnection();
			
			String welcomeMessage = (String) mServer.getAttribute(new ObjectName("quickstarts:type=AnnotatedComponentHelloWorld"), "WelcomeMessage");
			long count = (Long) mServer.getAttribute(new ObjectName("quickstarts:type=AnnotatedComponentHelloWorld"), "Count");
			
			log.info("{welcomeMessage:" + welcomeMessage + ", count: " + count + "}\n");
			
		} catch (Exception e) {

			log.severe("Error when trying to connect to sever. . .\n");
			e.printStackTrace();

		} finally {

			serviceURL = null;
			mServer = null;

			if (jmxConnector != null) {
				try {
					jmxConnector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
