package com.redhat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(serviceName = "HelloWorldService", portName = "HelloWorld", name = "HelloWorld", endpointInterface = "com.redhat.HelloWorldService",
targetNamespace = "http://www.jboss.org/eap/quickstarts/wshelloworld/HelloWorld")
public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String sayHello() {
		return "Hello World!";
	}

	@Override
	public String sayHelloToName(String name) {
		final List<String> names = new ArrayList<>();
		names.add(name);
		return sayHelloToNames(names);
	}

	@Override
	public String sayHelloToNames(List<String> names) {
		return "Hello " + createNameListString(names);
	}

	private String createNameListString(final List<String> names) {

		if (names == null || names.isEmpty()) {
			return "Anonymous!";
		}

		final StringBuilder nameBuilder = new StringBuilder();
		for (int i = 0; i < names.size(); i++) {

			if (i != 0 && i != names.size() - 1) {
				nameBuilder.append(", ");
			} else if (i != 0 && i == names.size() - 1) {
				nameBuilder.append(" & ");
			}
			
			nameBuilder.append(names.get(i));
		}

		nameBuilder.append("!");

		return nameBuilder.toString();
	}

	@Override
	public String throwException() throws Exception {
		try {
			
			Person p = null;
			return p.getName();
			
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}
	}

	@Override
	public List<BigInteger> getBigInteger() {
		MyData p = new MyData();
		return p.getBigIntegerList();
	}
}
