package com.example.client;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Stateless
public class ClientService {
  @Inject @RestClient private HelloService helloService;

  // Wrapping the REST client in this service works
  @Retry
  public String hello(String name) {
    return helloService.hello(name);
  }
}
