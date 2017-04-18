package br.com.pedrohos.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

/**
 * @author pedro-hos
 *
 */
@Singleton
@Startup
public class TimeoutServiceBean {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Resource
	private TimerService timerService;
	
	@Timeout
    public void scheduler(Timer timer) {
		logger.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " ] Invoke scheduler(Timer timer) method.");
    }

    @PostConstruct
    public void initialize(InvocationContext ctx) {
        ScheduleExpression se = new ScheduleExpression();
        se.hour("*").minute("*").second("0/20");
        timerService.createCalendarTimer(se, new TimerConfig("EJB timer service timeout at ", false));
    }

    @PreDestroy
    public void stop() {
        
    	logger.info("EJB Timer: Stop timers.");
        
    	timerService.getTimers().forEach(timer -> {
        	logger.info("Stopping timer: " + timer.getInfo());
            timer.cancel();
    	});
}
}
