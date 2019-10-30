package com.redhat.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.redhat.model.Evento;

@Path("/evento")
public class EventoResource {

	@GET
	@Produces("application/json")
	public List<Evento> todos() {
		return Evento.listAll();
	}

	@PUT
	@Consumes("application/json")
	@RolesAllowed("admin")
	@Transactional
	public void cria(Evento evento) {
		Evento.persist(evento);
	}

	@DELETE
	@Path("{id}")
	@RolesAllowed("admin")
	@Transactional
	public void apaga(@PathParam("id") long id) {
		Evento.delete("id", id);
	}

}
