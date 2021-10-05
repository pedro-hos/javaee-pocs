/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.ejb.elytron.controller;

import java.io.Serializable;
import java.util.Hashtable;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.redhat.ejb.elytron.GreeterEJB;

/**
 * A simple managed bean that is used to invoke the GreeterEJB and store the
 * response. The response is obtained by invoking getMessage().
 *
 * @author paul.robinson@redhat.com, 2011-12-21
 */
@Named("greeter")
@SessionScoped
@RolesAllowed({ "guest" })
@SecurityDomain("other")
public class Greeter implements Serializable {

    /** Default value included to remove warning. *	*/
    private static final long serialVersionUID = 1L;
    

    /**
     * Injected GreeterEJB client
     */
    //@EJB
    private GreeterEJB greeterEJB;

    /**
     * Stores the response from the call to greeterEJB.sayHello(...)
     */
    private String message;

    /**
     * Invoke greeterEJB.sayHello(...) and store the message
     *
     * @param name The name of the person to be greeted
     */
    
    public void setName(String name) {
    	
    	final Hashtable<String, String> jndiProperties = new Hashtable<>();
    	jndiProperties.put(Context.SECURITY_PRINCIPAL, "quickstartUser");
    	jndiProperties.put(Context.SECURITY_CREDENTIALS, "quickstartPwd1!");
    	
        Context context;
        
		try {
			context = new InitialContext(jndiProperties);
			greeterEJB = (GreeterEJB) context.lookup("java:global/ejb-interceptor-security/ejb-in-ear-ejb/GreeterEJB!" + GreeterEJB.class.getName());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        message = greeterEJB.sayHello(name);
    }

    /**
     * Get the greeting message, customized with the name of the person to be
     * greeted.
     *
     * @return message. The greeting message.
     */
    public String getMessage() {
        return message;
    }

}
