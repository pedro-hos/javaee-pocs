package com.redhat.ldap.elytron.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The remote client responsible for making invoking the intermediate bean to
 * demonstrate security context propagation in EJB to remote EJB calls.
 *
 * @author <a href="mailto:sguilhen@redhat.com">Stefan Guilhen</a>
 */
public class RemoteClient {

	public void callRemoteEjb() throws NamingException {

		/*
		 * SecuredEJBRemote reference = (SecuredEJBRemote)
		 * getInitialContext("127.0.0.1", 8080, "bwilson", "q1w2e3r4!")
		 * .lookup("ejb:/ejb-security/SecuredEJB!" + SecuredEJBRemote.class.getName());
		 */
		
		SecuredEJBRemote reference = (SecuredEJBRemote) getInitialContext().lookup("ejb:/ldap-application/SecuredEJB!com.redhat.ldap.elytron.ejb.SecuredEJBRemote");

		System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
		System.out.println("Successfully called secured bean, caller principal " + reference.getSecurityInfo());

		boolean hasAdminPermission = false;

		try {
			hasAdminPermission = reference.administrativeMethod();
		} catch (Exception e) {

			System.out.println("....");
		}

		System.out.println("\nPrincipal has admin permission: " + hasAdminPermission);
		System.out.println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");
	}

	public static Context getInitialContext(String host, Integer port, String username, String password) throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		props.put(Context.PROVIDER_URL, String.format("%s://%s:%d", "remote+http", host, port));
		if (username != null && password != null) {
			props.put(Context.SECURITY_PRINCIPAL, username);
			props.put(Context.SECURITY_CREDENTIALS, password);
		}
		return new InitialContext(props);
	}
	
	public static Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		return new InitialContext(props);
	}

	public static void main(String[] args) {

		try {
			new RemoteClient().callRemoteEjb();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * System.out.println("Hello!!!!");
		 * 
		 * Hashtable<String, String> jndiProperties = new Hashtable<>();
		 * //jndiProperties.put(Context.URL_PKG_PREFIXES,
		 * "org.jboss.ejb.client.naming");
		 * jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
		 * "org.wildfly.naming.client.WildFlyInitialContextFactory");
		 * //jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		 * 
		 * //jndiProperties.put(Context.SECURITY_PRINCIPAL, "quickstartUser");
		 * //jndiProperties.put(Context.SECURITY_CREDENTIALS, "quickstartPwd1!");
		 * 
		 * Context context = new InitialContext(jndiProperties);
		 * 
		 * SecuredEJBRemote reference = (SecuredEJBRemote)
		 * context.lookup("ejb:/ejb-security/SecuredEJB!" +
		 * SecuredEJBRemote.class.getName());
		 * 
		 * System.out.
		 * println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n"
		 * ); System.out.println("Successfully called secured bean, caller principal " +
		 * reference.getSecurityInfo());
		 * 
		 * boolean hasAdminPermission = false;
		 * 
		 * try { hasAdminPermission = reference.administrativeMethod(); } catch
		 * (Exception e) {
		 * 
		 * System.out.println("...."); }
		 * 
		 * System.out.println("\nPrincipal has admin permission: " +
		 * hasAdminPermission); System.out.
		 * println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n"
		 * );
		 * 
		 */
	}

}
