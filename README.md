#  Test-dev-java

## Clone
 ```sh
$ git clone https://github.com/blakrin/test-dev-java.git
$ cd test-dev-java 
```
## Setup

open application.properties on src/main/resources and add username and password. 
* spring.datasource.username=<username >
* spring.datasource.password=<password >


## Run 
```sh
$ ./mvnw clean package 
$ java -jar target/poker-game-api-0.0.1-SNAPSHOT.jar // to install 
```
if you have maven 3.9 installed 
 ```sh
$  mvn clean package 
$  java -jar target/poker-game-api-0.0.1-SNAPSHOT.jar // to install
```

## Test(Swagger)
 To test all apis open this link on your browser 
http://localhost:8080/swagger-ui.html 
 
## Tech
* *Java 8*
* *H2 database*
* *Spring boot 2.1.5 framework*
* *Junit 4* for units tests 
* *Maven 3* for code dependencies 
* *Git* Code version 
* *Swagger*

