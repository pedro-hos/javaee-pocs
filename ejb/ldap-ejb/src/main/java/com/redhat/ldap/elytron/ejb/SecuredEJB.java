package com.redhat.ldap.elytron.ejb;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "ldap-user" })
@SecurityDomain("other")
public class SecuredEJB implements SecuredEJBRemote {

	// Inject the Session Context
	@Resource
	private SessionContext ctx;

	/**
	 * Secured EJB method using security annotations
	 */
	public String getSecurityInfo() {
		// Session context injected using the resource annotation
		Principal principal = ctx.getCallerPrincipal();
		return principal.toString();
	}

	@RolesAllowed("ldap-admin")
	public boolean administrativeMethod() {
		return true;
	}
}
