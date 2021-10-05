package com.redhat.ejb.elytron.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 *
 */
public class UserInterceptor {
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		
		return context.proceed();
	}

}
