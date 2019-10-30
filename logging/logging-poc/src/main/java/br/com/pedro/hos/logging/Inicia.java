package br.com.pedro.hos.logging;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class Inicia {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void start() {
		log.info("org.slf4j.Logger ###### INFO #######");
		log.error("org.slf4j.Logger ###### ERROR ######");
		log.debug("org.slf4j.Logger ###### DEBUG ######");
	}

}
