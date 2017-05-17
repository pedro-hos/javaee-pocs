package br.com.pedrohos.slsb;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

@Stateless
@LocalBean
public class AsyncBean {
	
	private static final Logger log = Logger.getLogger(AsyncBean.class);
	
    @Asynchronous
    public void doSomething(){
    	
        log.info("Do Something Invoked. Sleeping for 15 seconds");        
        
        try{
            Thread.sleep(15000);
        } catch(Exception e){
        	log.info("Exception caught");        
            e.printStackTrace();
        }
        
        log.info("Do Something Done");        
    }

}
