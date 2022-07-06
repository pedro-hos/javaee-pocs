package org.jboss.quarkus;

import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *
 */
public class KeyStoreConfigSource implements ConfigSource {

    public Set<String> getPropertyNames() {
        return Set.of("db1.encryptionkey");
    }

    public String getValue(String propertyName) {
        System.out.println("I am hereeeee!!!!!!");
        return "db1.encryptionkey".equals(propertyName) ? "Ob8QvtbftVa7PPPFiSyyAA" : null;
    }

    public String getName() {
        return "file-vault-config-source";
    }

    @Override
    public int getOrdinal() {
        return 400;
    }

}
