package br.com.pedrohos.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.ejb.Timer;


/**
 * @author pedro-hos
 *
 */
@Singleton
public class Scheduler {

	Logger logger = Logger.getLogger(Scheduler.class.getName());

	//@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false, info = "adssads")
	public void doSomething(Timer timer) {
		logger.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
				+ " ] Invoke doSomething() method.");
	}

}
