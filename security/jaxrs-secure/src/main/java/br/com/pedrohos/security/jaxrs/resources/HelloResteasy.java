package br.com.pedrohos.security.jaxrs.resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author pedro-hos
 *
 */

/* Alternativa ao 'jboss-web.xml' */
//import org.jboss.security.annotation.SecurityDomain;
//@SecurityDomain("other")

@Path("hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface HelloResteasy {

	@GET
	@PermitAll
	Response hello();
	
	@GET
	@Path("/security")
	@RolesAllowed({"admin"})
	Response helloProtected();

}