#!/bin/bash

docker-compose run --rm --entrypoint "certbot renew" certbot
docker-compose exec nginx nginx -s reload
