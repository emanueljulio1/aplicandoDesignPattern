package com.emanuelkido;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
public class GreetingResource {

    @Inject
    @RestClient
    TimeService timeService;
    
    @Counted(name = "time.count")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Timeout(value = 500)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        delay = 2000,
        successThreshold = 4 
    )

    public String hello() throws InterruptedException {
        Thread.sleep(400);
        return "api =>" +  getTime();
    }

    @Timed(name = "time.timed")
    public String getTime(){
       return timeService.getTime();
    }

    private String fallback() {
        return "Caiu no fallback";
    }
}