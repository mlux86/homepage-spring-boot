#!/bin/bash -e

./mvnw package
docker-compose up -d --build --force-recreate 

