package com.redhat.ejb.elytron.interceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.wildfly.security.auth.server.SecurityDomain;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */
public class UserInterceptor {

	@Resource
	private SessionContext ctx;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		System.out.println("[UserInterceptor] " + ctx.getCallerPrincipal().getName());
		System.out.println("[UserInterceptor] " + SecurityDomain.getCurrent().getCurrentSecurityIdentity());

		return context.proceed();
	}

}
