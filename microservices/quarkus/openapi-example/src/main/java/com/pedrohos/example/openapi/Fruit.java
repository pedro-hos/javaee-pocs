package com.pedrohos.example.openapi;

/**
 * @author Pedro Silva <pesilva@redhat.com>
 * using https://quarkus.io/guides/openapi-swaggerui example
 */
public class Fruit {

	public String name;
	public String description;

	public Fruit() { }

	public Fruit(String name, String description) {
		this.name = name;
		this.description = description;
	}

}
