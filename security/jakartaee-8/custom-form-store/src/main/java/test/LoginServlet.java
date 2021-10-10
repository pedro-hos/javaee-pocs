package test;

import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

import java.io.IOException;

import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"*"}))
@WebServlet("/test")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 6727134101934346153L;

	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        
        request.getSession().invalidate();
        request.logout();
        
        AuthenticationParameters authenticationParameters = withParams().credential(credential);

        AuthenticationStatus status = securityContext.authenticate(request, response, authenticationParameters);
        
        log.info("Status ...." + status.name());
        log.info("request.getUserPrincipal().getName() ...." + request.getUserPrincipal().getName());
        log.info("securityContext.getCallerPrincipal().getName() ..." + securityContext.getCallerPrincipal());

        response.sendRedirect("/test/login.jsp?username=" + request.getUserPrincipal().getName());
    }

}