package org.jboss.quarkus.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * @author Pedro Silva
 *
 */
@Entity
public class User extends PanacheEntity {
    
    public User(String name, String email) {
        this.nome  = name;
        this.email = email;
    }
    
    public User() {}

    public String nome;
    public String email;

}
