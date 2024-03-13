package com.pedro.hos;

import io.quarkus.arc.Arc;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.UnsatisfiedResolutionException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

	@Inject
    Instance<ServiceName> serviceInstance;
	
	private ServiceName service;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive Country " + service.getName();
    }
    
    @PostConstruct
    void init() {
    	try {
    		service = serviceInstance.get();
		} catch (UnsatisfiedResolutionException e) {
			service = Arc.container().instance(DefaultService.class).get();
		}
    } 
}
