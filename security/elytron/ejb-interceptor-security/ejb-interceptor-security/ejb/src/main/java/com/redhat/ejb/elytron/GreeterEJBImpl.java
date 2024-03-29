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
package com.redhat.ejb.elytron;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.redhat.ejb.elytron.interceptor.HelloInterceptor;
import com.redhat.ejb.elytron.interceptor.UserInterceptor;

@Stateless
@RolesAllowed({ "guest" })
@SecurityDomain("other")
@Remote(GreeterEJB.class)
public class GreeterEJBImpl implements GreeterEJB {
	
    @Resource
    private SessionContext ctx;

	@Interceptors({ HelloInterceptor.class, UserInterceptor.class })
    public String sayHello(String name) {
		System.out.println("[GreeterEJBImpl] SessionContext Info: " + ctx.getCallerPrincipal().getName());
		System.out.println("[GreeterEJBImpl] isCallerInRole('guest')? " + ctx.isCallerInRole("guest"));
        return name;
    }
}
