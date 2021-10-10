package test;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;


@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.jsp",
                errorPage = "/login-error.html"
        )
)
@ApplicationScoped
public class AppConfig { }
