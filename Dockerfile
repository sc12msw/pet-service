FROM openjdk:11
COPY build/libs/pet-manager-service.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]
