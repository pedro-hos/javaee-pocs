package br.com.pedrohos.stateless;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author pedro-hos
 *
 */
@RunWith(Arquillian.class)
public class HelloServiceBeanTest {
	
	@EJB
	HelloService service;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
						 .addClasses(HelloService.class)
						 .addClass(HelloServiceBean.class)
						 .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
	}

	@Test
	public void testaComEJB() throws NamingException {
		String sayHello = service.sayHello();
		Assert.assertEquals("Hello World", sayHello);
	}
	
	@Test
	public void testaComJNDI() throws NamingException {
		
		final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        
        HelloService helloService = (HelloService) context.lookup("ejb:/ejb-pocs/HelloServiceBean!" + HelloService.class.getName());
		Assert.assertEquals("Hello World", helloService.sayHello());
	}
	
}
