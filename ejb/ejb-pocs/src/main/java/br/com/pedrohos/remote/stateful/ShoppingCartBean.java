package br.com.pedrohos.remote.stateful;

import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * @author pedro-hos
 *
 */
@Stateful
@Remote(ShoppingCart.class)
public class ShoppingCartBean implements ShoppingCart {

	Logger logger = Logger.getLogger(this.getClass().getName());

	private Collection<String> items = null;
	
	@Override
	@PostConstruct
	public void initialize() {
		this.items = new LinkedList<>();

	}

	@Override
	public void addItem(String item) {
		logger.info("### Add item " + item + " ###");
		this.items.add(item);

	}

	@Override
	public Collection<String> getItens() {
		return this.items;
	}

	@Remove
	@Override
	public void finished() {
		logger.info(">>> Remove method finished() invoked");

	}

	@Override
	@PrePassivate
	public void logPassivation() {
		logger.info(">>> PrePassivate method invoked");
	}

	@Override
	@PostActivate
	public void logActivation() {
		logger.info(">>> PostActivate method invoked");
	}

}
