# Sutton Framework and Demo Application

This repository contains the Sutton Framework and an application that uses the framework.

## Differences between the version used in the videos and this version

* It uses Java 21 (without any VM arguments to disable the Java module system)
* It uses Tomcat 10 (instead of Tomcat 8)
* It does not use Genson anymore but Jackson for JSON and XML serialization
* It uses Jersey 3 (instead of Jersey 2)
* It uses JUnit 5 (instead of JUnit 4)

### Sutton and Jersey

The Sutton classes implemented use Jersey.

## How to start the application

There are multiple methods to start the application. They are listed below:

### Use class `Start`

Execute method `main` in class `Start`. This will start the embedded Tomcat server and deploy the demo application. The application is available at `http://localhost:8080/demo/api`.

### Use Docker

### For manual testing 

To manually test, you first need to navigate to the directory and build the WAR file of the demo application using `mvn package`. Then execute `$> docker build .` (don't forget the point after `build`) to create an image. Finally, execute `$> docker run -p 8080:8080 --rm <IMAGE_ID>` to start the container.

### For integration testing (recommended)

Call `mvn verify` to start the integration tests. This will create a Docker image and start a container for the demo application. Then the integration tests will be executed. Finally, the container will be stopped and removed.

