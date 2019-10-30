package com.redhat.resources;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/fault")
public class FaultToleranceResource {

	private AtomicLong counter = new AtomicLong(0);
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Retry(maxRetries = 0)
	@Path("/retry")
	public String testFaultRetry() {
		long andIncrement = counter.getAndIncrement();
		maybeFail(andIncrement);
		return "successfull at " + andIncrement;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Timeout(value = 250)
	@Path("/timeout")
	@Fallback(fallbackMethod = "fallbackTimeout")
	public String testFaultTimeout() {
		
		long started = System.currentTimeMillis();
        final long invocationNumber = counter.getAndIncrement();
        
        try {
        	randomDelay();
        	return "successfull at " + invocationNumber;
        } catch (InterruptedException e) {
        	System.out.println("invocation " + invocationNumber + " and timeout " + (System.currentTimeMillis() - started));
			return null;
		}
	}
	
	public String fallbackTimeout() {
		return "fallbackTimeout";
	}
	
	private void maybeFail(long count) {
		if(new Random().nextBoolean()) {
			System.out.println("Fail at " + count);
			throw new RuntimeException("Resource failure.");
		}
	}
	
	private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }
	
}
