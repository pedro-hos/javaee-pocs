package br.com.pedrohos.remote.stateless;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.pedrohos.remote.stateless.TimerService;
import br.com.pedrohos.remote.stateless.TimerServiceBean;

/**
 * @author pedro-hos
 *
 */
@RunWith(Arquillian.class)
public class TimerServiceBeanTest {

	@EJB
	private TimerService service;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
						 .addClasses(TimerService.class)
						 .addClasses(TimerServiceBean.class)
						 .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
	}
	
	@Test
	public void testGetTime() {
		Assert.assertNotNull(service.getTime());
	}

}
