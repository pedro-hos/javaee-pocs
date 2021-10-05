package com.redhat.ejb.elytron.interceptor;

import java.time.LocalDateTime;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */
public class HelloInterceptor {
	

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		Object[] parameters = context.getParameters();
		String param = (String) parameters[0];
		param = param + " [Intercepted at: " + LocalDateTime.now() + " ]";
		parameters[0] = param;
		context.setParameters(parameters);
		
		try {
			return context.proceed();
		} catch (Exception e) {
			System.out.println("Error calling ctx.proceed in modifyGreeting()");
			return null;
		}

	}

}
