package com.example.client;

import java.net.URI;
import java.net.URISyntaxException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("client")
@RequestScoped
public class ClientResource {
  @Inject @RestClient private HelloService helloService;
  @Inject private ClientService clientService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String helloWorld() {
    // this method relies on the retry of the MP REST Client
    return helloService.hello("World");
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("retry")
  @Retry
  public String helloWorldRetry() {
    return helloService.hello("retry");
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("service")
  public String helloWorldService() {
    return clientService.hello("service");
  }

  @Path("programmatic")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String helloWorldProgrammatic() throws URISyntaxException {
    HelloService remoteApi =
        RestClientBuilder.newBuilder()
            .baseUri(new URI("http://localhost:8085/server"))
            .build(HelloService.class);
    return remoteApi.hello("Programmer");
  }
}
