package com.redhat;

import java.util.Arrays;
import java.util.List;

public class MyBean {

	private Integer id;
	private String name;

	public MyBean() { }
	
	public MyBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<MyBean> beans() {
		return Arrays.asList(new MyBean(1, "Pedro"), new MyBean(2, "Silva"));
	}

}
