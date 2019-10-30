package mail;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/email.do")
public class EnviaEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private Email email;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final String from = req.getParameter("from");
		final String to = req.getParameter("to");
		final String subject = req.getParameter("subject");
		final String content = req.getParameter("content");
		
		StringBuilder message = new StringBuilder();
		
		try {
			
			email.send(from, to, subject, content);
			message.append("Email enviado com sucesso");
			
		} catch (Exception e) {
			
			message.append("Erro ao enviar email");
			e.printStackTrace();
			
		} finally {
			
			req.setAttribute("message", message.toString());
			
			RequestDispatcher resultView = req.getRequestDispatcher("index.jsp");
			resultView.forward(req, resp);
		}
	}

}
