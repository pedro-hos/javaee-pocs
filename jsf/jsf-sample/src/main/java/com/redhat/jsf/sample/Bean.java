package com.redhat.jsf.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ViewScoped
@ManagedBean
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@PostConstruct
	public void init() {
		//go to an webservice to get information
		System.out.println("############ @PostConstruct");
		this.name = "Pedro";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
