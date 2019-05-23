package com.jugvale.rest;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jugvale.model.entities.Contact;
import com.jugvale.model.repositories.ContactRepository;

@ApplicationScoped
@Path("/api/agenda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendaResource {

	@Inject
    ContactRepository contactRepository;
	
	@GET
	public Response getContacts() {
		List<Contact> contact = contactRepository.findAll().list();
		
		if(contact.isEmpty()) {
            throw new WebApplicationException("No contacts available", HttpURLConnection.HTTP_NOT_FOUND);
		}
		
		return Response.ok(contact).build();
	}
	
	@GET
	@Path("/{name}")
	public Response getContactByName(@PathParam("name") final String name) {
		Contact contact = contactRepository.findByName(name);
		
		if(Objects.isNull(contact)) {
            throw new WebApplicationException("No contacts available", HttpURLConnection.HTTP_NOT_FOUND);
		}
		
		return Response.ok(contact).build();
	}
	
	@POST
	@Transactional
	public Response newContact(final Contact contact) {
		contactRepository.persist(contact);
		return Response.ok(contact).build();
	}
	
	@PUT
	@Transactional
	@Path("/{id}")
	public Response editContact(final Contact contact, @PathParam("id") final long id) {
		
		if(Objects.isNull(contactRepository.findById(id))) {
            throw new WebApplicationException("No contacts available", HttpURLConnection.HTTP_NOT_FOUND);
		}
		
		contact.id = id;
		contactRepository.persist(contact);
		
		return Response.ok(contact).build();
	}
	
	@DELETE
	@Transactional
	@Path("/{id}")
	public Response editContact(@PathParam("id") final long id) {
		
		Contact contact = contactRepository.findById(id);
		
		if(Objects.isNull(contact)) {
            throw new WebApplicationException("No contacts available", HttpURLConnection.HTTP_NOT_FOUND);
		}
		
		contactRepository.delete(contact);
		
		return Response.ok().build();
	}
}