package com.pedro.hos;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@LookupIfProperty(name = "country-name", stringValue = "FR")
public class ServiceNameFrenchCountry implements ServiceName {

	@Override
	public String getName() {
		return "FR";
	}

}
