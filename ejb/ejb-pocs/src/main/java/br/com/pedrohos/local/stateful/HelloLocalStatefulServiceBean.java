package br.com.pedrohos.local.stateful;

import javax.ejb.Local;
import javax.ejb.Stateful;

/**
 * @author pedro-hos
 *
 */
@Stateful
@Local(HelloLocalStatefulBean.class)
public class HelloLocalStatefulServiceBean implements HelloLocalStatefulBean {

	@Override
	public String sayHello(final String name) {
		return "Hello " + name;
	}

}
