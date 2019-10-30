package br.com.pedrohos.webservice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class HttpServletRequestInject {

	@Inject
	private HttpServletRequest request;
	
	public void getInfos() {
		
        final String infos = "IP: " + request.getRemoteAddr() +
                ", Port: " + request.getLocalPort() +
                ", Host: " + request.getRemoteHost();

        System.out.println("infos = " + infos);

	}

}