package com.jugvale.model.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.jugvale.model.entities.Contact;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact>{

	public Contact findByName(String name){
        return find("name", name).firstResult();
    }
	
}
