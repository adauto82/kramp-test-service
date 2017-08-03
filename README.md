# KrampTestService

How to start the KrampTestService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/kramp-test-service-1.0.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`, this is a simple operation menu with links to help debug if there are any issues with the application.

How to see the code
---

The entry point to the application is in org.kramp.testservice.KrampTestServiceApplication, there you will find the resources registered and the configuration management. 
