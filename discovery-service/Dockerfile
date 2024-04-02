FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /
COPY /src /spring-boot-starter-timer/src
COPY /pom.xml /spring-boot-starter-timer/
COPY /src /entity-service/src
COPY /pom.xml /entity-service/
COPY /src /discovery-service/src
COPY /pom.xml /discovery-service/
COPY /src /user-service/src
COPY /pom.xml /user-service/
COPY /src /media-service/src
COPY /pom.xml /media-service/
COPY /src /gateway-service/src
COPY /pom.xml /gateway-service/
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM openjdk:17-jdk-slim AS discovery-service
WORKDIR /
COPY --from=build /discovery-service/target/*.jar application.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","application.jar"]
