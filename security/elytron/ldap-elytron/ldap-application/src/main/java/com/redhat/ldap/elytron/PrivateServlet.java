package com.redhat.ldap.elytron;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */

@WebServlet("/private")
public class PrivateServlet extends HttpServlet {

	private static final long serialVersionUID = 8542428529747293268L;
	
	static String PAGE_HEADER = "<html><head><title>Private Page</title></head><body>";
    static String PAGE_FOOTER = "</body></html>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		writer.println(PAGE_HEADER);
		
		try {
			writer.println("This is a private page!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writer.println(PAGE_FOOTER);
		writer.close();

	}
}