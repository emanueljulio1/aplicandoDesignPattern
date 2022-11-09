package com.emanuelkido;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/time")
public class GreetingResource {

    @ConfigProperty(name="config")

    Optional<String> config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return config.orElse("vazio ") + ", " +  LocalDateTime.now() + "\n" ;
    }
}