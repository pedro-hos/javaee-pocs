package br.redhat.jmx.web.mbean;

import java.util.concurrent.atomic.AtomicLong;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import br.redhat.jmx.web.service.HelloService;

@Singleton
@Startup
public class AnnotatedComponentHelloWorld extends AbstractComponentMBean implements IAnnotatedHelloWorldMBean {

	private String welcomeMessage = "Hello";
	private AtomicLong count = new AtomicLong(0);

	@Inject
	HelloService helloService;

	public AnnotatedComponentHelloWorld() {
		super("quickstarts");
	}

	@Override
	public long getCount() {
		return count.get();
	}

	@Override
	public void setWelcomeMessage(String message) {
		if (message != null && message.trim().length() > 0)
			welcomeMessage = message;
	}

	@Override
	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	@Override
	public String sayHello(String name) {
		count.incrementAndGet();
		return helloService.createHelloMessage(welcomeMessage, name);
	}
}
