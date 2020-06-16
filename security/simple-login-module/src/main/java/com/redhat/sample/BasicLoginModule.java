package com.redhat.sample;

import java.security.acl.Group;

import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class BasicLoginModule extends UsernamePasswordLoginModule {

	@Override
	protected String getUsersPassword() throws LoginException {
		System.out.format("MyLoginModule: authenticating user '%s'\n", getUsername());
		String password = super.getUsername();
		password = password.toUpperCase();
		return password;
	}

	@Override
	protected boolean validatePassword(String inputPassword, String expectedPassword) {
		String encryptedInputPassword = (inputPassword == null) ? null : inputPassword.toUpperCase();
		System.out.format("Validating that (encrypted) input psw '%s' equals to (encrypted) '%s'\n" , encryptedInputPassword, expectedPassword);
		return true;

	}

	@Override
	protected Group[] getRoleSets() throws LoginException {

		SimpleGroup group = new SimpleGroup("Roles");

		try {

			System.out.println("Search here group for user: " + super.getUsername());

			group.addMember(new SimplePrincipal("Manager"));

		} catch (Exception e) {
			throw new LoginException("Failed to create group member for " + group);
		}

		return new Group[] { group };

	}

}
