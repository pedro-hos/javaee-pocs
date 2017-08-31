package br.com.pedrohos.webservice;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("hello")
public class SimpleServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4683374850686308218L;
	
	@Inject
	private HttpServletRequestInject service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.getInfos();
	}

}
