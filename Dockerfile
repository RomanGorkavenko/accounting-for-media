FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /
COPY /spring-boot-starter-timer/src /spring-boot-starter-timer/src
COPY /spring-boot-starter-timer/pom.xml /spring-boot-starter-timer/
COPY /entity-service/src /entity-service/src
COPY /entity-service/pom.xml /entity-service/
COPY /discovery-service/src /discovery-service/src
COPY /discovery-service/pom.xml /discovery-service/
COPY /config-service/src /config-service/src
COPY /config-service/pom.xml /config-service/
COPY /user-service/src /user-service/src
COPY /user-service/pom.xml /user-service/
COPY /media-service/src /media-service/src
COPY /media-service/pom.xml /media-service/
COPY /gateway-service/src /gateway-service/src
COPY /gateway-service/pom.xml /gateway-service/
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM openjdk:17-jdk-slim AS config-service
WORKDIR /
COPY --from=build /config-service/target/*.jar application.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","application.jar"]

FROM openjdk:17-jdk-slim AS discovery-service
WORKDIR /
COPY --from=build /discovery-service/target/*.jar application.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","application.jar"]

FROM openjdk:17-jdk-slim AS user-service
WORKDIR /
COPY --from=build /user-service/target/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]

FROM openjdk:17-jdk-slim AS media-service
WORKDIR /
COPY --from=build /media-service/target/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]

FROM openjdk:17-jdk-slim AS gateway-service
WORKDIR /
COPY --from=build /gateway-service/target/*.jar application.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","application.jar"]
