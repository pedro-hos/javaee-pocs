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
	
	org.jboss.logging.Logger logTest = org.jboss.logging.Logger.getLogger(getClass());
		
	Logger log = LoggerFactory.getLogger(getClass());

	static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

	static String PAGE_FOOTER = "</body></html>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("org.slf4j.Logger ###### INFO #######");
		log.error("org.slf4j.Logger ###### ERROR ######");
		log.debug("org.slf4j.Logger ###### DEBUG ######");
		
		logTest.debug("org.jboss.logging.Logger ###### DEBUG #######");
		logTest.error("org.jboss.logging.Logger ###### ERROR ######");
		logTest.info("org.jboss.logging.Logger ###### INFO ######");
		
		try {
			
			String a = null;
			a.concat("a");
			
			System.out.println(a);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<h1>Hello World</h1>");
		writer.println(PAGE_FOOTER);
		writer.close();
	}

}
