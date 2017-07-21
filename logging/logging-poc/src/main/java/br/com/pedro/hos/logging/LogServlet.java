package br.com.pedro.hos.logging;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/HelloWorld")
public class LogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//Logger log = Logger.getLogger(getClass().getName());
	
	Logger log = LoggerFactory.getLogger(getClass());

	static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

	static String PAGE_FOOTER = "</body></html>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("###### Logging no Servlet #######");
		log.error("SEVERE ERROR no Servlet");
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<h1>Hello World</h1>");
		writer.println(PAGE_FOOTER);
		writer.close();
	}

}
