package br.com.pedrohos.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author pedro-hos
 *
 */
@Stateless
@Remote(HelloService.class)
public class HelloServiceBean implements HelloService {

	@Override
	public String sayHello() {
		return "Hello World";
	}

}
