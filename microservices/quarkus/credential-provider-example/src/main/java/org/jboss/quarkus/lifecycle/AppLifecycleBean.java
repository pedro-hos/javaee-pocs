package org.jboss.quarkus.lifecycle;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.jboss.quarkus.model.User;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

/**
 * @author Pedro Silva
 */

@ApplicationScoped
public class AppLifecycleBean {
    
    private static final Logger LOGGER = Logger.getLogger(AppLifecycleBean.class);
    
    @Transactional
    void onStart(@Observes StartupEvent ev) {               
        LOGGER.info("The application is starting...");
        createUsers();
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }
    
    private void createUsers() {
        LOGGER.info("Creating User...");
        User.persist(Arrays.asList(new User("Pedro", "pedro@email.com"), 
                                   new User("William", "bill@email.com"),
                                   new User("Chris", "chris@email.com"),
                                   new User("Patrick", "patrick@email.com")));
    }

}
