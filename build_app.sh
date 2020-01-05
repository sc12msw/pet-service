./gradlew bootJar
docker build -f Dockerfile -t pet-service:dev .
docker-compose up -d