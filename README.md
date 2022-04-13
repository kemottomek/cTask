# Cisco test - spring boot and rest controller application

***
# Standalone spring-boot application.
Stack:
* Java 11
* Maven 
* Spring Boot

## How build, test and run

***
To prepare standalone jar:

```
.\mvnw clean verify
```

To generate test report run:

```
.\mvnw clean surefire-report:report
```
To run spring boot:
```
.\.\mvnw clean spring-boot:run
```
## Properties to set up
Properties are in docker-compose file 

## Run locally in docker container 
Build image and run it

#Running the whole environment
```
docker-compose up
```

#Stopping the environment
You can stop the environment by pressing Ctrl+C in the terminal that displays logs.
You can also execute docker-compose stop on another terminal but from the same directory.
If the environment not stopping gracefully you can press Ctrl+C again to kill all the containers (or execute docker-compose kill)


#Resetting the environment
If you would like to reset the local environment to a clean state (with examples app registered) stop the environment and execute docker-compose rm.

#Local endpoint
http://localhost:8080/h2-console
http://localhost:8081/actuator/

#TODO
* Rest in exposed behind API Gateway which should deliver security layer