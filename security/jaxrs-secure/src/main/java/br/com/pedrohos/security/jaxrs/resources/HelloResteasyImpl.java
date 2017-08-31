package br.com.pedrohos.security.jaxrs.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import br.com.pedrohos.security.jaxrs.ejb.HelloEJB;

/**
 * 
 * @author pedro-hos
 *
 */
@Stateless
public class HelloResteasyImpl implements HelloResteasy {

	@Inject
	private HelloEJB helloEjb;
	
	@Override
	public Response hello() {
		return Response.ok().entity(helloEjb.sayHello()).build();
	}

	@Override
	public Response helloProtected() {
		return Response.ok().entity(helloEjb.sayHello()).build();
	}
}
