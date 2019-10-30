package br.com.pedrohos.remote.stateless;

import javax.ejb.EJB;
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

import br.com.pedrohos.remote.stateless.HelloService;
import br.com.pedrohos.remote.stateless.HelloServiceBean;

/**
 * @author pedro-hos
 *
 */
@RunWith(Arquillian.class)
public class HelloServiceBeanTest {
	
	@EJB
	private HelloService service;

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
	
}
