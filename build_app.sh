mvn clean package
docker build -f ockerfile -t feedme-producer:dev .
docker build -f feedme-consumer/Dockerfile -t feedme-consumer:dev .
docker-compose up -d