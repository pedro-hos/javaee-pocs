package com.redhat.gss.jsec;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */
@WebServlet(name = "PokemonServlet", urlPatterns = "/example/pokemon")
public class PokemonServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6674592536202696503L;
	
	static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";
	static String PAGE_FOOTER = "</body></html>";
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	PokeService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("Getting Pokemon infos ....");
		
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<p>" + service.getInfo("bulbasaur") + "</p>");
		writer.println(PAGE_FOOTER);
		writer.flush();
		
	}
	
}
