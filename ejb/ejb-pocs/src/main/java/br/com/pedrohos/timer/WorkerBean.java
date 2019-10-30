package br.com.pedrohos.timer;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.Singleton;

@Singleton
public class WorkerBean {
	
	@AccessTimeout(unit = TimeUnit.SECONDS, value = 9)
	public void exemplo() throws InterruptedException {
		System.out.println("Timer work started");
        Thread.sleep(12000);
        System.out.println("Timer work done");
	}

}
