#!/bin/bash

echo -e "manuel deployment"

maven_deployment(){
  # git pull httppp
  # cd isim
  mvn clean package -DskipTests
}

maven_deployment

docker_manuel(){
  docker-compose up
  # docker-compose up -d
}
docker_manuel