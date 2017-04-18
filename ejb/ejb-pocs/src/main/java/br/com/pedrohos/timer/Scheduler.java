package br.com.pedrohos.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.jboss.logging.Logger;

/**
 * @author pedro-hos
 *
 */
@Singleton
public class Scheduler {
	
	private final Logger logger = Logger.getLogger(getClass());

	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void doSomething() {
		logger.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " ] Invoke doSomething() method.");
	}
	
}
