package org.jboss;

import java.security.Principal;
import java.util.Properties;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
@Stateless
@RolesAllowed({ "guest" })
@SecurityDomain("legacy-domain")
@Remote(SecuredEJBRemote.class)
public class SecuredEJB implements SecuredEJBRemote {

    @Resource
    private SessionContext ctx;

    @Override
    public String getSecurityInformation() {
        Principal principal = ctx.getCallerPrincipal();
        return principal.toString();
    }

    @Override
    @RolesAllowed("admin")
    public boolean administrativeMethod() {
        return true;
    }

    @Override
    public void connectLdap() {
       
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.SECURITY_AUTHENTICATION, "simple");
        props.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        props.put(Context.SECURITY_CREDENTIALS, "secret");
        props.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        DirContext ldapCtx;

        try {

            ldapCtx = new InitialDirContext(props);
            
            String[] attrIDs = { "cn" };
            SearchControls searchControls = new SearchControls();
            searchControls.setReturningAttributes(attrIDs);
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            
            String filter = "objectClass=person";
            String name = "uid=user1,dc=people,dc=example,dc=com";
            
            NamingEnumeration<SearchResult> search = ldapCtx.search(name, filter, searchControls);
            
            String commonName = null;
            String distinguishedName = null;
            
            if (search.hasMore()) {
                
                SearchResult result = (SearchResult) search.next();
                Attributes attrs = result.getAttributes();
                
                distinguishedName = result.getNameInNamespace();
                commonName = attrs.get("cn").toString();
                
                System.out.println(commonName);
                System.out.println(distinguishedName);
            }
            
            ldapCtx.close();
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
    }

}
