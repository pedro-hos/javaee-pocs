package com.pedro.hos;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultService implements ServiceName {

	@Override
	public String getName() {
		return "Default";
	}

}
