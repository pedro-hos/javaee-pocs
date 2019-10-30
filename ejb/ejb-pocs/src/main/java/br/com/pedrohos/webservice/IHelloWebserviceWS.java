package br.com.pedrohos.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.jboss.org/eap/quickstarts/wshelloworld/HelloWorld")
public interface IHelloWebserviceWS {

	/**
	 * Say hello as a response
	 *
	 * @return A simple hello world message
	 */
	@WebMethod
	String sayHello();

	/**
	 * Say hello to someone precisely
	 *
	 * @param name
	 *            The name of the person to say hello to
	 * @return the number of current bookings
	 */
	@WebMethod
	String sayHelloToName(String name);

	/**
	 * Say hello to a list of people
	 *
	 * @param names
	 *            The list of names to say hello to
	 * @return the number of current bookings
	 */
	@WebMethod
	String sayHelloToNames(List<String> names);

}
