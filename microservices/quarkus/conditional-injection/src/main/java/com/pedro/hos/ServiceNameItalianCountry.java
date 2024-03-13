package com.pedro.hos;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@LookupIfProperty(name = "country-name", stringValue = "IT", lookupIfMissing = true)
public class ServiceNameItalianCountry implements ServiceName {

	@Override
	public String getName() {
		return "IT";
	}

}
