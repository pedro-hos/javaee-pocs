package org.jboss;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
public interface SecuredEJBRemote {
	String getSecurityInformation();
    boolean administrativeMethod();
    void connectLdap();
}
