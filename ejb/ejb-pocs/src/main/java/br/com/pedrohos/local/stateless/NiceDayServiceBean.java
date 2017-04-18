package br.com.pedrohos.local.stateless;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author pedro-hos
 *
 */
@Stateless
@Local(NiceDayService.class)
public class NiceDayServiceBean implements NiceDayService {

	@Override
	public String getMessage() {
		return " - Have a nice day" ;
	}

}
