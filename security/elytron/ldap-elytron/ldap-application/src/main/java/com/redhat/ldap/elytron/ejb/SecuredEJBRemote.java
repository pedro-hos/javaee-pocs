package com.redhat.ldap.elytron.ejb;

/**
 * The interface used to access the SecuredEJB.
 *
 * @author <a href="mailto:sguilhen@redhat.com">Stefan Guilhen</a>
 */

public interface SecuredEJBRemote {

	/**
	 * @return A String containing the name of the current principal.
	 */
	String getSecurityInfo();

	boolean administrativeMethod();

}
