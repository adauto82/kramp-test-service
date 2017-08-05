# KrampTestService

Requirements
---

1. JAVA 8
1. maven 3

How to start the KrampTestService application
---

1. Run `mvn clean install` to build your application
1. Run `mvn package` to package your application
1. Check config.yml and set your values
1. Start application with `java -jar target/kramp-test-service-1.0.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`, this is a simple operation menu with links to help debug if there are any issues with the application.

How to see the code
---

The entry point to the application is in org.kramp.testservice.KrampTestServiceApplication, there you will find the resources registered and the configuration management. 

Why i ussed Dropwizzard
---

I choosed [Dropwizard](https://github.com/dropwizard/dropwizard) because it simplified the building of the API using Jersey that was my first choice for REST and camed with an ops-friendly library called metrics, developed by them. Also i wanted to tested because i read a lot of recommendations for it and i just did 3 years of spring API’s so i wanted something different and promising. 
The process wasn't painless but the downsides that i expirienced aren't that bad.

I have more reasons but i wanted to keep this brief.
