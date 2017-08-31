package br.com.pedrohos.remote.stateless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.com.pedrohos.local.stateless.NiceDayService;

/**
 * @author pedro-hos
 *
 */

@Stateless
public class TimerServiceBean implements TimerService {
	
	private final Logger logger = Logger.getLogger(TimerServiceBean.class.getName());
	
	@EJB
	private NiceDayService niceDay;
	
	@Resource
	private SessionContext ctx;
	
	@PostConstruct
	public void init() {
		logger.info(">>> Call init() method!");
	}

	@Override
	public String getTime() {
		logger.info(">>> " + ctx.getInvokedBusinessInterface());
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + niceDay.getMessage();
	}
	
	@PreDestroy
	public void preDestroy() {
		logger.info(">>> Call preDestroy() method!");
	}

}
