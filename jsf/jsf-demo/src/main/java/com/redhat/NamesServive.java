package com.redhat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@ApplicationScoped
//@RequestScoped
public class NamesServive {

	Logger logger = Logger.getLogger(NamesServive.class.getName());
	
	private List<String> names;
	
	@PostConstruct
	public void startup( ) {
		logger.info("Initiazing NameService class ...");
		this.setNomes(new ArrayList<>());
	}
	
	public void addName(final String name) {
		this.names.add(name);
	}

	public List<String> getNomes() {
		return names;
	}

	public void setNomes(List<String> nomes) {
		this.names = nomes;
	}
	
	
}
