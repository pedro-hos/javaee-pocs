package br.com.pedrohos.security.jaxrs.ejb;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * 
 * @author pedro-hos
 *
 */

@Local
@Stateless
@RolesAllowed({"admin"})
public class HelloEJB {
	
	@Resource
	private SessionContext ctx;

	public String sayHello() {
		return "Hello World: ".concat(ctx.getCallerPrincipal().toString());
	}
	
}
