package br.com.pedrohos.security.jaxrs.resources;

import javax.ws.rs.core.Response;

/**
 * 
 * @author pedro-hos
 *
 */
public class HelloResteasyImpl implements HelloResteasy {

	@Override
	public Response hello() {
		return Response.ok("Hello World").build();
	}

}
