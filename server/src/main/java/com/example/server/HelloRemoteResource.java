package com.example.server;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class HelloRemoteResource {
  private static final Logger LOGGER = Logger.getLogger(HelloRemoteResource.class.getName());

  @Path("{name}")
  @GET
  public String hello(@PathParam("name") String name) {
    // check if we're down
    if (isDown()) {
      LOGGER.warning(() -> String.format("Service unavailable, name: %s", name));
      throw new ServiceUnavailableException();
    }
    LOGGER.info(() -> String.format("Service available, name: %s", name));
    return "Hello " + name + "!";
  }

  private boolean isDown() {
    // approximate 80% chance
    return Math.random() > 0.2;
  }
}
