package org.pedro.hos.jaas;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class JAASLoginModule implements LoginModule {

	Subject subject = null;
	CallbackHandler callbackHandler = null;
	Map<String, ?> sharedState = null;
	Map<String, ?> options = null;
	
	private String username;
	private char[] password;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
	}

	@Override
	public boolean login() throws LoginException {
		
		if (callbackHandler == null) {
			throw new LoginException("Error: no CallbackHandler available "
					+ "to garner authentication information from the user");
		}
		
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("username");
		callbacks[1] = new PasswordCallback("password: ", false);

		try {
			
			callbackHandler.handle(callbacks);
			username = ((NameCallback) callbacks[0]).getName();
			password = ((PasswordCallback) callbacks[1]).getPassword();
			
			System.out.println("Username: " + username + " Password: " + password.toString());
			
		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());

		} catch (UnsupportedCallbackException uce) {
			throw new LoginException("Error: " + uce.getCallback().toString()
					+ " not available to garner authentication information "
					+ "from the user");

		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}
