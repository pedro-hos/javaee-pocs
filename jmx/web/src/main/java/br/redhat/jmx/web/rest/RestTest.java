package br.redhat.jmx.web.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("rest-test/")
public class RestTest {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response keycloak( ) {
		return Response.ok("Hello").build();
	}

}
