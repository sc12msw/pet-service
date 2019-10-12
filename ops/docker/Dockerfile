FROM openjdk:8-jdk-alpine
COPY ./build/libs/pet-manager-service.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]