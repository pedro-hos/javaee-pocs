EJB LDAP Elytron configuration
===

This project have the following files:

~~~
$ ls -al
total 48
drwxr-xr-x. 3 pesilva pesilva  4096 May 10 17:37 .
drwxr-xr-x. 6 pesilva pesilva  4096 May 10 17:37 ..
-rw-r--r--. 1 pesilva pesilva  2267 May 10 17:25 configure.cli
-rwxrwxr-x. 1 pesilva pesilva  4967 May 10 17:34 pom.xml
drwxrwxr-x. 3 pesilva pesilva  4096 May 10 17:31 src
-rw-rw-r--. 1 pesilva pesilva 21065 May 10 17:29 users.ldif
~~~

* configure.cli: with the configuration necessary to connect and configure the EJB and LDAP for the LDAP configuration present on `users.ldif`. This file, contains the following content, and **you need to change the LDAP configuration to match to your LDAP configurations**:

~~~
# Batch script to configure LDAP Elytron in the EAP 7.4 to secure EJB server

# Start batching commands
batch

# Create the directory context.
/subsystem=elytron/dir-context=ldap-dir-context:add(url="ldap://127.0.0.1:10389",principal="uid=admin,ou=system",credential-reference={clear-text="secret"}, referral-mode=follow)

# Create an LDAP realm and security domain.
/subsystem=elytron/ldap-realm=ldap-realm:add(dir-context=ldap-dir-context,direct-verification="true", identity-mapping={rdn-identifier="uid", search-base-dn="ou=People,dc=keycloak,dc=org", user-password-mapper={from="userPassword"}, use-recursive-search=false, attribute-mapping=[{filter-base-dn="ou=Roles,dc=keycloak,dc=org",filter="(&(objectClass=groupOfNames)(member={1}))",from="cn",to="Roles"}]})

# Add role decoder that decodes roles from the Roles attribute.
/subsystem=elytron/simple-role-decoder=from-roles-attribute:add(attribute=Roles)


# Add a security domain that uses the LDAP realm
/subsystem=elytron/security-domain=ldap-security-domain:add(realms=[{realm=ldap-realm,role-decoder=from-roles-attribute}],default-realm=ldap-realm,permission-mapper=default-permission-mapper)

# Create a sasl-authentication-factory for CLI and remoting-connectorand set the authentication mechanism to PLAIN
/subsystem=elytron/configurable-sasl-server-factory=ldap-sasl-server-factory:add(sasl-server-factory=elytron)

/subsystem=elytron/sasl-authentication-factory=ldap-application-sasl-authentication:add(mechanism-configurations=[{mechanism-name=PLAIN, mechanism-realm-configurations=[{realm-name=ldap-realm}]}],sasl-server-factory=ldap-sasl-server-factory,security-domain=ldap-security-domain)


# Add security domain mapping in the EJB3 subsystem to enable elytron LDAP for the EJBs
/subsystem=ejb3/application-security-domain=other:add(security-domain=ldap-security-domain)

# Update the http-remoting-connector to use the sasl-authentication-factory created before
/subsystem=remoting/http-connector=http-remoting-connector:write-attribute(name=sasl-authentication-factory,value=ldap-application-sasl-authentication)
/subsystem=remoting/http-connector=http-remoting-connector:undefine-attribute(name=security-realm)

# Run the batch commands
run-batch

# Reload the server configuration
reload
~~~

* users.ldif: Is my LDAP structure, only for your reference
* pom.xml: Have the maven dependencies to run the EJB example
* src/: contains the Java example

### Step 1 - Configure the LDAP and EJB connection on your EAP 7.1+

**After having changed the LDAP configuration on configure.cli to match your LDAP configurations**, you will start your EAP 7.1+ and run the following command:

~~~
sh bin/jboss-cli.sh --connect --file=path_to/ldap-ejb-elytron/configure.cli
~~~

### Step 2 - Configure the Java project to use the EJB Secured.

You can just, run the `mvn clean install` command at the project attached. The things that you should to know:

* RemoteClient.java: Is the class that will call the Remote EJB. You can see two `getInitialContext()` methods as follow. The first one is providing host, port, username, and password. However the other doesn't, because these values are provided by the `src/main/resources/META-INF/wildfly-config.xml` file, and the example is using that configuration file, instead hardcoded.

~~~
public static Context getInitialContext(String host, Integer port, String username, String password) throws NamingException { ... }
		
public static Context getInitialContext() throws NamingException { ... }
~~~

The method `callRemoteEjb()` is calling our Remote EJB:

~~~
	public void callRemoteEjb() throws NamingException {

		SecuredEJBRemote reference = (SecuredEJBRemote) getInitialContext().lookup("ejb:/ldap-ejb-elytron/SecuredEJB!com.redhat.ldap.elytron.ejb.SecuredEJBRemote");

		System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n");
		System.out.println("Successfully called secured bean, caller principal " + reference.getSecurityInfo());

		boolean hasAdminPermission = false;

		try {
			hasAdminPermission = reference.administrativeMethod();
		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println("\nPrincipal has admin permission: " + hasAdminPermission);
		System.out.println("\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n\n");
	}
~~~

The `SecuredEJBRemote` is the interface, that `SecuredEJB` implements. The class accept the **ldap-user** role, from our LDAP, and is using the **other** secured domain configured with: `/subsystem=ejb3/application-security-domain=other:add(security-domain=ldap-security-domain)`). Otherwise, the `administrativeMethod()` method is configured to only accept user with **ldap-admin** role, as configured with the `@RolesAllowed("ldap-admin")`

~~~
@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "ldap-user" })
@SecurityDomain("other")
public class SecuredEJB implements SecuredEJBRemote {

	// Inject the Session Context
	@Resource
	private SessionContext ctx;

	/**
	 * Secured EJB method using security annotations
	 */
	public String getSecurityInfo() {
		// Session context injected using the resource annotation
		Principal principal = ctx.getCallerPrincipal();
		return principal.toString();
	}

	@RolesAllowed("ldap-admin")
	public boolean administrativeMethod() {
		return true;
	}
}
~~~

### Step 3 - Testing

First, you should build and deploy the application on your side. Then you should run the following command at the project root path:

```
mvn exec:exec
```

This will run the `RemoteClient.java` main class, and will use the username and password from ``. If your user had the admin permission, you will see:

~~~
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


May 10, 2022 5:33:04 PM org.xnio.Xnio <clinit>
INFO: XNIO version 3.8.4.Final-redhat-00001
May 10, 2022 5:33:04 PM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.8.4.Final-redhat-00001
May 10, 2022 5:33:04 PM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.4.0.Final-redhat-00001
May 10, 2022 5:33:04 PM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 5.0.20.SP1-redhat-00001
Successfully called secured bean, caller principal jbrown

Principal has admin permission: true


* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
~~~

otherwise, will see:

~~~
May 10, 2022 6:07:56 PM org.xnio.Xnio <clinit>
INFO: XNIO version 3.8.4.Final-redhat-00001
May 10, 2022 6:07:56 PM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.8.4.Final-redhat-00001
May 10, 2022 6:07:56 PM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.4.0.Final-redhat-00001
May 10, 2022 6:07:56 PM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 5.0.20.SP1-redhat-00001

Successfully called secured bean, caller principal bwilson
javax.ejb.EJBAccessException: WFLYEJB0364: Invocation on method: public abstract boolean com.redhat.ldap.elytron.ejb.SecuredEJBRemote.administrativeMethod() of bean: SecuredEJB is not allowed
(...)

Principal has admin permission: false


* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
~~~

Just for your information, the `mvn exec:exec` comes from the following code from `pom.xml`:

~~~
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-ejb-plugin</artifactId>
	<configuration>
		<ejbVersion>3.2</ejbVersion>
		<generateClient>true</generateClient>
	</configuration>
</plugin>

<!-- Add the Maven exec plug-in to allow us to run a Java program via  Maven -->
<plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>exec-maven-plugin</artifactId>
	<configuration>
		<executable>java</executable>
		<workingDirectory>${project.build.directory}/exec-working-directory</workingDirectory>
		<arguments>
			<!-- automatically creates the classpath using all project dependencies, 
				also adding the project build directory -->
			<argument>-classpath</argument>
			<classpath />
			<argument>com.redhat.ldap.elytron.ejb.RemoteClient</argument>
		</arguments>
	</configuration>
	<executions>
		<execution>
			<goals>
				<goal>exec</goal>
			</goals>
		</execution>
	</executions>
</plugin>
~~~