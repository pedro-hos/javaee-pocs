package br.com.pedrohos.slsb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
//@Startup
public class SimpleBean {

	private static final Logger log = Logger.getLogger(SimpleBean.class.getName());

	@Resource
	private TimerService timerService;

	@EJB
	private AsyncBean aBean;

	private String getJBossNodeName() {
		return System.getProperty("jboss.node.name");
	}

	@PostConstruct
	private void initialize() {
		timerService.createIntervalTimer(10000, 1000, new TimerConfig(null, false));
	}

	// @Timeout
	public void showRunning() {
		log.info("Timer is active " + getJBossNodeName());
		aBean.doSomething();
	}

	@Timeout
	public void showRunning2() {

		log.info("Timer is active " + getJBossNodeName());

		for (int i = 0; i < 20; i++) {
			log.info("count " + i);
			aBean.doSomething();
		}
	}

	public void invoke(String text) {
		log.info("Bean invocation " + getJBossNodeName() + " " + text);
	}

}
