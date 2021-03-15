/**
 * 
 */
package br.com.pedrohos.security.jaxrs.resources;

import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author pedro-hos@outlook.com
 *
 */
@RunWith(Arquillian.class)
public class HelloResteasyImplTest {
	
	/**
     * Constructs a deployment archive
     *
     * @return the deployment archive
     */
    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "jax-rs-arquillian-test.war")
            .addClasses(HelloResteasy.class)
            .addClass(HelloResteasyImpl.class)
            .addClass(ResourceActivator.class)
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void getHelloWorldMessage(@ArquillianResteasyResource HelloResteasy helloResource) {
    	Response hello = helloResource.hello();
    	System.out.println(hello.getEntity().toString());
    }

}
