package br.com.pedrohos.slsb;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;


import br.com.pedrohos.timer.WorkerBean;

@Singleton
//@Startup
public class SingletonWithTimeout {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private TimerService timerService;
	
	@EJB
	private WorkerBean worker;
	
	@PostConstruct
	private void initialize() {
		timerService.createIntervalTimer(10000, 1000, new TimerConfig(null, false));
	}
	
    @Timeout
    @AccessTimeout(unit = TimeUnit.SECONDS, value = 9)
	public void doSomething(Timer timer) {
		logger.info("[ SingletonWithTimeout ]");
		try {
			worker.exemplo();
		} catch (InterruptedException e) {
			System.out.println(">>>>>>>>>>><<<<<<<<<<<<<<<<<<<");
		}
	}
    
    

}
