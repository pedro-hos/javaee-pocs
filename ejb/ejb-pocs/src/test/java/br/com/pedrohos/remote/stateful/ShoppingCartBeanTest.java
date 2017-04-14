package br.com.pedrohos.remote.stateful;

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

/**
 * @author pedro-hos
 *
 */
@RunWith(Arquillian.class)
public class ShoppingCartBeanTest {
	
	@EJB
	private ShoppingCart shopping;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
						 .addClasses(ShoppingCart.class)
						 .addClasses(ShoppingCartBean.class)
						 .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
	}

	@Test
	public void testWithoutSleep() {
		
		Assert.assertTrue(shopping.getItens().isEmpty());
		
		shopping.addItem("Pão de ló");
		shopping.addItem("Ovo de Páscoa");
		shopping.addItem("Creme corporal");
		
		Assert.assertFalse(shopping.getItens().isEmpty());
		
		System.out.println("\n#### Items ####");
		
		shopping.getItens().forEach(System.out :: println);
		
		shopping.finished();
		
	}
	
	@Test
	public void testWithSleep() {
		
		Assert.assertTrue(shopping.getItens().isEmpty());
		
		shopping.addItem("Pão de ló");
		
		sleep(59000L);
		
		shopping.addItem("Ovo de Páscoa");
		shopping.addItem("Creme corporal");
		
		Assert.assertFalse(shopping.getItens().isEmpty());
		
		System.out.println("\n#### Items ####");
		
		shopping.getItens().forEach(System.out :: println);
		
		shopping.finished();
		
	}
	
	private void sleep(Long time) {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
