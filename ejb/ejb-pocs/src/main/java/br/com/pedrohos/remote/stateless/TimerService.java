package br.com.pedrohos.remote.stateless;

import javax.ejb.Remote;

/**
 * 
 * @author pedro-hos
 *
 */
@Remote
public interface TimerService {
	
	String getTime();

}
