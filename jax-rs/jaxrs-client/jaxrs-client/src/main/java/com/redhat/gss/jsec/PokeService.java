/**
 * 
 */
package com.redhat.gss.jsec;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author pesilva
 *
 */
@Stateless
public class PokeService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public String getInfo(String name) {
		 Client client = ClientBuilder.newClient();
		 return client.target("https://pokeapi.co/api/v2/pokemon/" +  name)
				 				.request(MediaType.APPLICATION_JSON)
				 				.get(String.class);
		 
	}

}
