package com.redhat.elytron;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
@SuppressWarnings("serial")
@WebServlet("/secure")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Admin" }))
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        Principal principal = null;
        String authType = null;

        principal = req.getUserPrincipal();

        authType = req.getAuthType();

        writer.println("<html><head><title>servlet-security</title></head><body>");
        writer.println("<h1>" + "Successfully called Secured Servlet " + "</h1>");
        writer.println("<p>" + "Principal  : " + principal.getName() + "</p>");
        writer.println("<p>" + "Authentication Type : " + authType + "</p>");
        writer.println("</body></html>");
        writer.close();
    }

}
