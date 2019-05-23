package com.jugvale.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name = "agenda")
public class Contact extends PanacheEntity {

	@NotNull
	@Size(min = 3, max = 100)
    @Column(nullable = false)
	public String nome;
	
	public String email;
	
	@Size(min = 2, max = 2)
	public Integer ddd;
	
	@Size(min = 8, max = 9)
	public Integer telephone;
	
}
