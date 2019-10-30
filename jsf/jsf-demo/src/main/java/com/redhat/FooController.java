package com.redhat;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FooController {
	
	@Inject
	private NamesServive namesService;

	public NamesServive getNamesService() {
		return namesService;
	}

	public void setNamesService(NamesServive namesService) {
		this.namesService = namesService;
	}

}
