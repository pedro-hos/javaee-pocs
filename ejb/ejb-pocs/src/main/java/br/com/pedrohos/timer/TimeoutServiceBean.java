package br.com.pedrohos.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.InvocationContext;


/**
 * @author pedro-hos
 *
 */
@Singleton
//@Startup
public class TimeoutServiceBean {

	Logger logger = Logger.getLogger(TimeoutServiceBean.class.getName());
	
	@Resource
	private TimerService timerService;
	
	@Timeout
    public void scheduler(Timer timer) {
		
		System.out.println("estou aqui!!");
		
		try {
			test();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " ] Invoke scheduler(Timer timer) method.");
	}

    @PostConstruct
    public void initialize(InvocationContext ctx) {
        ScheduleExpression se = new ScheduleExpression();
        se.hour("*").minute("*").second("0/20");
        timerService.createCalendarTimer(se, new TimerConfig("EJB timer service timeout at ", false));
    }
    
    @Asynchronous
    public void test() throws InterruptedException {
    	Thread.sleep(30000);
    	System.out.println("okokookokkokokokokokokok");
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
