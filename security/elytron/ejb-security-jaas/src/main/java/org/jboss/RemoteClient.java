package org.jboss;

import java.util.Hashtable;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
public class RemoteClient {

    public static void main(String[] args) throws Exception {

        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8180,remote+http://localhost:8280,remote+http://localhost:8380,remote+http://localhost:8480,remote+http://localhost:8580");
        final Context context = new InitialContext(jndiProperties);

        SecuredEJBRemote reference = (SecuredEJBRemote) context.lookup("ejb:/ejb-security-jaas/SecuredEJB!" + SecuredEJBRemote.class.getName());

        System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
        System.out.println("Called secured bean, caller principal " + reference.getSecurityInformation());
        
        boolean hasAdminPermission = false;
        
        try {
            
            hasAdminPermission = reference.administrativeMethod();
            System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
            reference.connectLdap();
            
        } catch (EJBAccessException e) {
            
        }
        
        System.out.println("\nPrincipal has admin permission: " + hasAdminPermission);
        System.out.println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");

    }

}
