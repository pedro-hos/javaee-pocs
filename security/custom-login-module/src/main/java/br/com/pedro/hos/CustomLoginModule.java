package br.com.pedro.hos;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class CustomLoginModule implements LoginModule {

	Subject subject = null;
	CallbackHandler callbackHandler = null;
	Map<String, ?> sharedState = null;
	Map<String, ?> options = null;
	
	private String username;
	private char[] password;
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		
		System.out.println("initialize(...) ");
		
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
	}

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
			
			throw new LoginException("Erro acontecendo no meu LoginModule");
			
		} catch (java.io.IOException ioe) {
			
			throw new LoginException(ioe.toString());
			
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException("Error: " + uce.getCallback().toString()
					+ " not available to garner authentication information "
					+ "from the user");

		} catch (Exception e) {
			throw new LoginException(e.getMessage());
}
		//return false;
	}

	public boolean commit() throws LoginException {
		System.out.println("commit()");
		return false;
	}

	public boolean abort() throws LoginException {
		System.out.println("abort()");
		return false;
	}

	public boolean logout() throws LoginException {
		System.out.println("logout()");
		return false;
	}

}
