package com.redhat.ldap.elytron.ejb;

import java.util.Properties;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
public class RemoteClient {
	
	public static void main(String[] args) {
		try {
			new RemoteClient().callRemoteEjb();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void callRemoteEjb() throws NamingException {

		SecuredEJBRemote reference = (SecuredEJBRemote) getInitialContext().lookup("ejb:/ldap-ejb-elytron/SecuredEJB!com.redhat.ldap.elytron.ejb.SecuredEJBRemote");

		System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
		System.out.println("Successfully called secured bean, caller principal " + reference.getSecurityInfo());

		boolean hasAdminPermission = false;

		try {
			hasAdminPermission = reference.administrativeMethod();
		} catch (EJBAccessException e) { /*You can add some bussiness rule here*/}

		System.out.println("\nPrincipal has admin permission: " + hasAdminPermission);
		System.out.println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");
	}

	public static Context getInitialContext(String host, Integer port, String username, String password)
			throws NamingException {
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

}
