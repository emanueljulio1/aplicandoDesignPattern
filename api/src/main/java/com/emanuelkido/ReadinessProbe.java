package com.emanuelkido;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.oracle.svm.core.annotate.Inject;

@Readiness
public class ReadinessProbe implements HealthCheck {
    @Inject
    @RestClient
    TimeService timeService; 

    @Override
    public HealthCheckResponse call() {

        if(timeService.getTime() == null){
            return HealthCheckResponse.down( "não estou pronto :/ ");
        } else{
            return HealthCheckResponse.up( "estou pronto :-) ");

        }
    }
    
}
