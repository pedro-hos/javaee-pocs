package br.com.pedrohos.remote.stateless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

/**
 * @author pedro-hos
 *
 */

@Stateless
public class TimerServiceBean implements TimerService {
	
	private final Logger logger = Logger.getLogger(TimerServiceBean.class);
	
	@Resource
	private SessionContext ctx;
	
	@PostConstruct
	public void init() {
		logger.info(">>> Call init() method!");
	}

	@Override
	public String getTime() {
		logger.info(">>> " + ctx.getInvokedBusinessInterface());
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	@PreDestroy
	public void preDestroy() {
		logger.info(">>> Call preDestroy() method!");
	}

}
