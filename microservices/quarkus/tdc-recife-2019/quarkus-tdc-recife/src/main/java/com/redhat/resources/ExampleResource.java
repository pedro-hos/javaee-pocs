package com.redhat.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.redhat.model.Message;

@Path("/hello")
public class ExampleResource {
	
	@ConfigProperty(defaultValue = "Hello World", name = "hello.message")
	String message;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
		return Response.ok(new Message(this.message)).build();
    }
}