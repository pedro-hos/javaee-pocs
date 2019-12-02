package com.redhat;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.jboss.org/eap/quickstarts/wshelloworld/HelloWorld")
public interface HelloWorldService {

	@WebMethod
	String sayHello();
	
	@WebMethod
	String throwException() throws Exception;

	@WebMethod
	String sayHelloToName(String name);

	@WebMethod
	String sayHelloToNames(List<String> names);

}
