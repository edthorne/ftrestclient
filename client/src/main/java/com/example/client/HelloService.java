package com.example.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/** @author Ondrej Mihalyi */
@Path("/api/hello")
@Produces(
    MediaType
        .TEXT_PLAIN) // Produces and Consumes for all methods, the can also be on a specific method
@Consumes(MediaType.TEXT_PLAIN)
@RegisterRestClient // Required to enable injection of this interface
public interface HelloService {
  @Path("{name}")
  @GET
  String hello(@PathParam("name") String name);
}
