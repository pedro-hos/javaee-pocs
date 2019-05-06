package br.redhat.jmx.web.mbean;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public abstract class AbstractComponentMBean {

	private static final Logger log = Logger.getLogger(AbstractComponentMBean.class.getName());

	private final String domain;
	private String name;
	private MBeanServer mbeanServer;
	private ObjectName objectName = null;

	public AbstractComponentMBean(String domain) {
		super();
		this.domain = domain;
	}

	@PostConstruct
	protected void startup() {
		this.name = this.getClass().getSimpleName();
		try {
			objectName = new ObjectName(domain, "type", name);
			mbeanServer = ManagementFactory.getPlatformMBeanServer();
			mbeanServer.registerMBean(this, objectName);
		} catch (Exception e) {
			throw new IllegalStateException("Error during registration of " + name + " into JMX:" + e, e);
		}
	}

	@PreDestroy
	protected void destroy() {
		log.info("# << -- Destroy : " + this.name);
		try {
			mbeanServer.unregisterMBean(this.objectName);
		} catch (Exception e) {
			throw new IllegalStateException("Error during unregistration of " + name + " into JMX:" + e, e);
		}
	}

}
