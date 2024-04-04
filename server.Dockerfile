FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /
COPY /spring-boot-starter-timer/src /spring-boot-starter-timer/src
COPY /spring-boot-starter-timer/pom.xml /spring-boot-starter-timer/
COPY /entity-service/src /entity-service/src
COPY /entity-service/pom.xml /entity-service/
COPY /discovery-service/src /discovery-service/src
COPY /discovery-service/pom.xml /discovery-service/
COPY /user-service/src /user-service/src
COPY /user-service/pom.xml /user-service/
COPY /media-service/src /media-service/src
COPY /media-service/pom.xml /media-service/
COPY /gateway-service/src /gateway-service/src
COPY /gateway-service/pom.xml /gateway-service/
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM openjdk:17-jdk-slim AS discovery-service
WORKDIR /
COPY --from=build /discovery-service/target/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]
