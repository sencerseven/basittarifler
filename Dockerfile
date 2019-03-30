FROM openjdk:8-jdk-alpine
ADD target/spring-basittarifler.jar spring-basittarifler.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-basittarifler.jar"]