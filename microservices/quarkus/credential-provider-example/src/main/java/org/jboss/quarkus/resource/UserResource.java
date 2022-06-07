package org.jboss.quarkus.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.quarkus.model.User;

/**
 * @author Pedro Silva
 *
 */
@Path("/user")
public class UserResource {
    
    @GET
    @Produces("application/json")
    public List<User> allUsers() {
        return User.listAll();
    }

}
