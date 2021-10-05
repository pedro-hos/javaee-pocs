package com.redhat.ejb.elytron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redhat.ejb.elytron.GreeterEJB;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */

@WebServlet("/test-interceptor")
public class InterceptorServlet extends HttpServlet {

	private static final long serialVersionUID = 8542428529747293268L;

	static String PAGE_HEADER = "<html><head><title>Public Page</title></head><body>";
	static String PAGE_FOOTER = "</body></html>";

	//@EJB
	private GreeterEJB greeterEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
		Principal principal = null;
		String authType = null;
		String remoteUser = null;

		// Get security principal
		principal = req.getUserPrincipal();
		// Get user name from login principal
		remoteUser = req.getRemoteUser();
		// Get authentication type
		authType = req.getAuthType();

		writer.println(PAGE_HEADER);
		writer.println("<h1>" + "Successfully called Secured Servlet " + "</h1>");
		writer.println("<p>" + "Principal  : " + principal.getName() + "</p>");
		writer.println("<p>" + "Remote User : " + remoteUser + "</p>");
		writer.println("<p>" + "Authentication Type : " + authType + "</p>");

		writer.println("<hr>");

		writer.println("<p>" + "From EJB and Interceptor  : " + getEJB(principal.getName()) + "</p>");
		//writer.println("<p>" + "From EJB and Interceptor  : " + greeterEJB.sayHello(principal.getName()) + "</p>");
		
		writer.println(PAGE_FOOTER);
		writer.close();

	}
	
	private String getEJB(String name) {

		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "quickstartUser");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "quickstartPwd1!");

		Context context;

		try {
			context = new InitialContext(jndiProperties);
			greeterEJB = (GreeterEJB) context.lookup(
					"java:global/ejb-interceptor-security/ejb-in-ear-ejb/GreeterEJB!" + GreeterEJB.class.getName());
			return greeterEJB.sayHello(name);
		} catch (NamingException e) {
			e.printStackTrace();
			return "ERROR...";
		}

	}

}
