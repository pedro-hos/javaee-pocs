package com.pedrohos.example.openapi;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 * using https://quarkus.io/guides/openapi-swaggerui example
 */
@Path("/fruits")
public class FruitResource {
	
	private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @GET
    @Operation(summary = "Get List of Sample records for given Type", 
    		   deprecated = false, 
    		   hidden = false, 
    		   description = "Text description", 
    		   operationId = "get_Fruit")
    public Set<Fruit> list() {
        return fruits;
    }

    @POST
    @Operation(summary = "Add Fruit Example", 
	   deprecated = false, 
	   hidden = false, 
	   description = "Add new fruit", 
	   operationId = "add_Fruit")
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    @Operation(summary = "Remove Fruit Example", 
	   deprecated = false, 
	   hidden = false, 
	   description = "Remove a fruit", 
	   operationId = "remove_Fruit")
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }

}
