# MicroProfile REST Client with Fault Tolerance

This is a multi-module project demonstrating the combined use of MicroProfile REST Client with
Fault Tolerance. It's divided into two projects to provide separation of the client and server
components.

## Building

To build the WAR files simply execute `mvn package`. This will build both the client and server
applications.

## Execution

The projects are expected to run as follows:

* Client: Port 8086
* Server: Port 8085

If you need them to run on different ports, you'll need to adjust the ports located in the
following files:

* `pom.xml`
* `client/src/main/java/com/example/client/ClientResource.java`
* `client/src/main/resources/META-INF/microprofile-config.properties`

Both projects can be executed by using the Payara Micro Maven plugin goals:

* `mvn payara-micro:start`
* `mvn payara-micro:stop`
