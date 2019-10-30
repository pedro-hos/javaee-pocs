package br.com.pedrohos.remote.stateful;

import java.util.Collection;

/**
 * @author pedro-hos
 *
 */
public interface ShoppingCart {
	
	void initialize();
	void addItem(final String item);
	Collection<String> getItens();
	void finished();
	
	void logPassivation();
	void logActivation();
	
}
