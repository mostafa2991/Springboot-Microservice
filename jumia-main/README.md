# Country Phone Code Checker

It is a single page application in Java that shows a customers list categorized by country and phone state (valid or not valid).

## Run 
```
mvn spring-boot:run
```
The home page can be access in http://localhost:8080.

## Docker Image 

Create project jar:
```
mvn clean install
```

Build Docker image:
```
docker build -t country-phone .
```

Run Docker image:
```
docker run -d -p 8080:8080 --name country-phone-container country-phone:latest

```

## Technologies
Below the technologies used in this project:

* Java 8 - programming language (current long-term support release version).
* Thymeleaf - Used for create home page template.
* Bootstrap 4 - Used for page CSS. 
* Spring Boot Data JPA - Used to access SQlite data.
* SQlite - Customers database.
* MockMVC - Template tests.
* JUnit5 - Unit tests.
* Lombok - Avoid boilerplate java code.
* MapStruct - Avoid boilerplate copy values code.
* Docker - Create a file for build a project image.
