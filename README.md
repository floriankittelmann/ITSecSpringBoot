# Demonstration Project for TSM ITSec

This project uses Spring Boot with Spring MVC, Thymeleaf and Spring Data JPA. The basic configuration was generated using
[spring initializer](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.5.6&packaging=jar&jvmVersion=17&groupId=com.example.mse.itsec&artifactId=demonstration-service&name=demonstration-service&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.mse.itsec.demonstration-service&dependencies=web,thymeleaf,devtools,lombok,h2,data-jpa,validation).

The as backing database [H2 in memory](https://www.baeldung.com/spring-boot-h2-database) DB is used.

Resources used XXS protection:
 
 - [Thymeleaf form submission](https://spring.io/guides/gs/handling-form-submission/)
 - [Validation in Spring Boot](https://www.baeldung.com/spring-boot-bean-validation)
 - [Hibernate Bean Validation](http://hibernate.org/validator/)
 - [Spring Boot Wep App Security](https://docs.spring.io/spring-security/site/docs/5.1.6.RELEASE/reference/html/web-app-security.html)
 - [Spring Security Security Headers](https://www.baeldung.com/spring-prevent-xss)
 - [Unsave output in Thymeleaf](https://stackoverflow.com/questions/14876926/how-to-prevent-thymeleaf-from-replacing-special-characters-in-html-attribute-val)
 - [Spring Security CSRF protection](https://www.baeldung.com/spring-security-csrf)
 