package com.redhat;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class GreetController {

	private String username;

	@Inject
	private NamesServive namesService;
	
	public void addUser() {
		getNamesService().addName(username);
	}
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
	
	public NamesServive getNamesService() {
		return namesService;
	}

	public void setNamesService(NamesServive namesService) {
		this.namesService = namesService;
	}

}
