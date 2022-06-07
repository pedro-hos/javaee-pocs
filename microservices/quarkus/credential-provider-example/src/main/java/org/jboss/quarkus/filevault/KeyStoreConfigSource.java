package org.jboss.quarkus.filevault;

import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.quarkus.aes256.AES256;

/**
 * @author Pedro Silva
 *
 */
public class KeyStoreConfigSource implements ConfigSource {

    @Override
    public Set<String> getPropertyNames() {
        return Set.of("db1.storepassword");
    }

    @Override
    public String getValue(String propertyName) {
        return "db1.storepassword".equals(propertyName) ? getStoredPassword("R2sDgtkybxqlwf79ZX+qOQ==") : null;
    }

    @Override
    public String getName() {
        return "file-vault-config-source";
    }
    
    /* Retrieve the Stored password. Here it is possible recovery the value from datasource, 
     * if the passeword will be used for another thing that datasource, or read from some file, 
     * or also encript the "password" such as base64 and decript here. For example as follow, using AES-256 */
    
    private String getStoredPassword(final String password256) {
        return AES256.decrypt(password256);
    }
    
    public String getStoredPassword() {
        return "storepassword";
    }

}
