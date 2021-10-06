	package com.redhat.ejb.elytron;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * Simple secured EJB using EJB security annotations
 *
 * @author Sherif Makary
 *
 */
/**
 *
 * Annotate this EJB for authorization. Allow only those in the "guest" role. For EJB authorization, you must also specify the
 * security domain. This example uses the "other" security domain which is provided by default in the standalone.xml file.
 *
 */
@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "guest" })
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

    @RolesAllowed("admin")
    public boolean administrativeMethod() {
        return true;
    }
}
