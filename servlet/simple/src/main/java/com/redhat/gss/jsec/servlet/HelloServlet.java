package com.redhat.gss.jsec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/example/hello")
public class HelloServlet extends HttpServlet {
	
	Logger log = Logger.getLogger(getClass().getName());

	private static final long serialVersionUID = 1L;
	static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";
	static String PAGE_FOOTER = "</body></html>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("#### Saying Hello!");

		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<html>Hello, I am a Java servlet!</html>");
		writer.println(PAGE_FOOTER);
		writer.flush();
		
	}

}
