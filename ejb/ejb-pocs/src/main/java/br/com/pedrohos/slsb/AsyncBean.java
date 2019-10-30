package br.com.pedrohos.slsb;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AsyncBean {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
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
