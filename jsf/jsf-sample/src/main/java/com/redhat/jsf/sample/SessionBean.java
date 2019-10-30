package com.redhat.jsf.sample;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
@SuppressWarnings("deprecation")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SessionBean.class.getName());

	@PostConstruct
	public void init() {
		
		log.log(Level.INFO, "Constructing Bean");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("name", UUID.randomUUID().toString());

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
