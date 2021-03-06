package com.redhat.jsf.sample;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

@ViewScoped
@ManagedBean
@SuppressWarnings("deprecation")
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Bean.class.getName());

	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Constructing Bean");
	}

	public String getName() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		return (String) session.getAttribute("name");
	}

	@PreDestroy
	public void preDestroy() {
		log.log(Level.INFO, "Destroying Bean");
	}

}
