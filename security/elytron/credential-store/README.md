Retrieve existing credential store from code
=============================================================

_based on: [existing-credential-store-query](https://github.com/wildfly-security-incubator/elytron-examples/tree/c9014af753fc5b5a2e6d12889c16b8956eca3e3e/existing-credential-store-query) example_

This example demonstrates how to programmatically query credential store that was created with WildFly CLI.

Add a credential store with aliases via WildFly CLI:

```shell
/subsystem=elytron/credential-store=my_credential_store:add(location="PATH/TO/credential-store.cs", credential-reference={clear-text=StorePassword},create=true)
{"outcome" => "success"}
/subsystem=elytron/credential-store=my_credential_store:add-alias(alias=my-secret-db-password, secret-value="db-secret")
{"outcome" => "success"}
/subsystem=elytron/credential-store=my_credential_store:add-alias(alias=my-secret-access-password, secret-value="access-pw")
{"outcome" => "success"}
```
Credential Store has been created and 2 aliases `my-secret-access-password` and `my-secret-db-password` have been added.

Look at `RetrieveCredentialStore` class to see how this credential store can be queried from code.

Run this command to execute this class:
```shell
    $ mvn clean install
    $ java -jar target/credential-store.jar PATH/TO/credential-store.cs my-secret-access-password StorePassword
```

* The first paramenter is the credential-store file
* The second paramenter is the alias that you want to retriece
* The third paramenter is the keystore password

Expected output:
```
************************************
Current Aliases in credential store:
- my-secret-db-password
- my-secret-access-password
************************************
Your secret key for alias my-secret-db-password is: db-secret
```

**NOTE** this example uses clear text credentials. Do not use in production.