package com.redhat.elytron;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
@SuppressWarnings("serial")
@WebServlet("/public")
public class PublicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>servlet-public</title></head><body>");
        writer.println("<h1>" + "Successfully called Public Servlet " + "</h1>");
        writer.println("</body></html>");
        writer.close();
    }

}
