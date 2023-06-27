package com.pedro;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import org.wildfly.security.auth.server.IdentityCredentials;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.credential.store.CredentialStore;
import org.wildfly.security.credential.store.CredentialStoreException;
import org.wildfly.security.credential.store.WildFlyElytronCredentialStoreProvider;
import org.wildfly.security.credential.store.CredentialStore.CredentialSourceProtectionParameter;
import org.wildfly.security.credential.store.CredentialStore.ProtectionParameter;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.ClearPassword;

public class RetrieveCredentialStore {
	
	private static final Provider CREDENTIAL_STORE_PROVIDER = new WildFlyElytronCredentialStoreProvider();
	private static final Provider PASSWORD_PROVIDER = new WildFlyElytronPasswordProvider();
	
	static {
		Security.addProvider(PASSWORD_PROVIDER);
	}
	
	public static void main(String[] args) {
		
		
		try {
			
			String location = args[0];
			String alias = args[1];
			String keystorePass = args[2];
		
			retrieveValues(location, alias, keystorePass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CredentialStoreException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
	        e.printStackTrace();
	    }
		
	}
	
	public static void retrieveValues(final String location, final String queriedAlias, final String keystorePass) throws NoSuchAlgorithmException, CredentialStoreException {
		/*
         * Create a ProtectionParameter for access to the store.
         */
        Password storePassword = ClearPassword.createRaw(ClearPassword.ALGORITHM_CLEAR, keystorePass.toCharArray());
        ProtectionParameter protectionParameter = new CredentialSourceProtectionParameter(IdentityCredentials.NONE.withCredential(new PasswordCredential(storePassword)));
        
        // Get an instance of the CredentialStore
        CredentialStore credentialStore = CredentialStore.getInstance("KeyStoreCredentialStore", CREDENTIAL_STORE_PROVIDER);

        // Initialise the CredentialStore
        Map<String, String> configuration = new HashMap<>();
        configuration.put("location", location);
        configuration.put("modifiable", "true");
        configuration.put("keyStoreType", "JCEKS");
        credentialStore.initialize(configuration, protectionParameter);

        System.out.println("************************************");
        System.out.println("Current Aliases in credential store: ");
        
        for (String alias : credentialStore.getAliases()) {
            System.out.print(" - ");
            System.out.println(alias);
        }
        
        System.out.println("************************************");

        final PasswordCredential credential = credentialStore.retrieve(queriedAlias,PasswordCredential.class);
        final Password password = credential.castAndApply(PasswordCredential.class, ClearPassword.ALGORITHM_CLEAR, PasswordCredential::getPassword);
        final ClearPassword clearPassword = password.castAs(ClearPassword.class, ClearPassword.ALGORITHM_CLEAR);
        System.out.println("Your secret key for alias " + queriedAlias + " is: " + new String(clearPassword.getPassword()));
	}
}
