package com.redhat.ldap.elytron.ejb;

/**
 * 
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
public interface SecuredEJBRemote {
	String getSecurityInfo();
	boolean administrativeMethod();
}
