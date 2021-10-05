package com.redhat.ejb.elytron.interceptor;

import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.wildfly.security.auth.server.SecurityDomain;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */
public class HelloInterceptor {
	
	@Resource
    private SessionContext ctx;
	
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		Object[] parameters = context.getParameters();
		String param = (String) parameters[0];
		param = param + " [Intercepted at: " + LocalDateTime.now() + " ]";
		parameters[0] = param;
		context.setParameters(parameters);
		
		System.out.println("[HelloInterceptor] " + ctx.getCallerPrincipal().getName());
		
		System.out.println("[HelloInterceptor] " + SecurityDomain.getCurrent().getCurrentSecurityIdentity());
		
		try {
			return context.proceed();
		} catch (Exception e) {
			System.out.println("Error calling ctx.proceed in modifyGreeting()");
			return null;
		}

	}

}
