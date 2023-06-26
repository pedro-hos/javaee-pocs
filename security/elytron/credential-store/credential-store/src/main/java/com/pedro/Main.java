package com.pedro;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.wildfly.security.auth.server.IdentityCredentials;
import org.wildfly.security.credential.KeyPairCredential;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.credential.PublicKeyCredential;
import org.wildfly.security.credential.SecretKeyCredential;
import org.wildfly.security.credential.store.CredentialStore;
import org.wildfly.security.credential.store.CredentialStore.CredentialSourceProtectionParameter;
import org.wildfly.security.credential.store.CredentialStore.ProtectionParameter;
import org.wildfly.security.credential.store.CredentialStoreException;
import org.wildfly.security.credential.store.WildFlyElytronCredentialStoreProvider;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.ClearPassword;

public class Main {

	private static final Provider CREDENTIAL_STORE_PROVIDER = new WildFlyElytronCredentialStoreProvider();
	private static final Provider PASSWORD_PROVIDER = new WildFlyElytronPasswordProvider();

	static {
		Security.addProvider(PASSWORD_PROVIDER);
	}

	private static void populateCredentialStore(final CredentialStore credentialStore) throws Exception {
		// Clear Password
		Password clearPassword = ClearPassword.createRaw(ClearPassword.ALGORITHM_CLEAR,
				"ExamplePassword".toCharArray());
		credentialStore.store("clearPassword", new PasswordCredential(clearPassword));

		// SecretKey
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		SecretKey secretKey = keyGenerator.generateKey();
		credentialStore.store("secretKey", new SecretKeyCredential(secretKey));

		// KeyPair
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048, SecureRandom.getInstanceStrong());
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		credentialStore.store("keyPair", new KeyPairCredential(keyPair));

		// PublicKey
		credentialStore.store("publicKey", new PublicKeyCredential(keyPair.getPublic()));
		credentialStore.flush();
	}

	private static void retrieveCredentials(final CredentialStore credentialStore) throws Exception {
		Password password = credentialStore.retrieve("clearPassword", PasswordCredential.class).getPassword();
		SecretKey secretKey = credentialStore.retrieve("secretKey", SecretKeyCredential.class).getSecretKey();
		KeyPair keyPair = credentialStore.retrieve("keyPair", KeyPairCredential.class).getKeyPair();
		PublicKey publicKey = credentialStore.retrieve("publicKey", PublicKeyCredential.class).getPublicKey();

		System.out.println(password);

	}

	private static Password giveMeAPass(String alias)
			throws NoSuchAlgorithmException, CredentialStoreException, InvalidKeySpecException {
		/*
		 * Create a ProtectionParameter for access to the store.
		 */
		Password storePassword = ClearPassword.createRaw(ClearPassword.ALGORITHM_CLEAR, "mypassword".toCharArray());

		ProtectionParameter protectionParameter = new CredentialSourceProtectionParameter(
				IdentityCredentials.NONE.withCredential(new PasswordCredential(storePassword)));

		CredentialStore credentialStore = CredentialStore.getInstance("KeyStoreCredentialStore",
				CREDENTIAL_STORE_PROVIDER);
		// Configure and Initialise the CredentialStore
		// String configPath = System.getProperty("jboss.server.config.dir");
		Map<String, String> configuration = new HashMap<>();

		String path = "/home/pesilva/Workspace/opt/redhat/eap/jboss-eap-7.4/standalone/data/credentials/csstore.jceks";
		configuration.put("keyStoreType", "JCEKS");
		configuration.put("location", path);
		configuration.put("modifiable", "false");

		// Inicialize credentialStore
		credentialStore.initialize(configuration, protectionParameter);

		return credentialStore.retrieve(alias, PasswordCredential.class).getPassword();
	}

	public static void credentialExample() throws Exception {

		/*
		 * Create a ProtectionParameter for access to the store.
		 */
		Password storePassword = ClearPassword.createRaw(ClearPassword.ALGORITHM_CLEAR, "StorePassword".toCharArray());
		ProtectionParameter protectionParameter = new CredentialSourceProtectionParameter(
				IdentityCredentials.NONE.withCredential(new PasswordCredential(storePassword)));
		// Get an instance of the CredentialStore
		CredentialStore credentialStore = CredentialStore.getInstance("KeyStoreCredentialStore",
				CREDENTIAL_STORE_PROVIDER);
		// Configure and Initialise the CredentialStore
		Map<String, String> configuration = new HashMap<>();
		configuration.put("location", "mystore.cs");
		configuration.put("create", "true");

		credentialStore.initialize(configuration, protectionParameter);

		populateCredentialStore(credentialStore);

		System.out.println("************************************");
		System.out.println("Current Aliases: -");
		for (String alias : credentialStore.getAliases()) {
			System.out.print(" - ");
			System.out.println(alias);
		}
		System.out.println("************************************");

		retrieveCredentials(credentialStore);

	}

	public static void main(String[] args) throws Exception {
		Password pass = giveMeAPass("database-pw");
		System.out.println(pass);
	}

}
