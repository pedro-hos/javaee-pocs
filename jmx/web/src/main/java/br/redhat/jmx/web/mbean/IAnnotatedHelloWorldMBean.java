package br.redhat.jmx.web.mbean;

import javax.management.MXBean;

@MXBean
public interface IAnnotatedHelloWorldMBean {
	
    long getCount();
    void setWelcomeMessage(String message);
    String getWelcomeMessage();
    String sayHello(String name);
}
