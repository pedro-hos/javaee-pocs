package br.com.pedro.hos.logging;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Inicia {
	
	Logger log = Logger.getLogger(getClass().getName());
	
	@PostConstruct
	public void start() {
		log.info("###### Logging start with @PostConstruct #######");
		log.severe("SEVERE ERROR no @PostConstruct");
	}

}
