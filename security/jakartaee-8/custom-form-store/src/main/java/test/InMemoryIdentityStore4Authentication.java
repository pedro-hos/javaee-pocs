package test;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@ApplicationScoped
public class InMemoryIdentityStore4Authentication implements IdentityStore {

    private Map<String, String> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(InMemoryIdentityStore4Authentication.class);

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        log.info("User logged in: " + credential.getCaller());
        return new CredentialValidationResult(credential.getCaller());
    }
    
    
}
