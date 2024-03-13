package com.pedro.hos;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@LookupIfProperty(name = "country-name", stringValue = "DEFAULT")
public class DefaultService implements ServiceName {

	@Override
	public String getName() {
		return "Default";
	}

}
