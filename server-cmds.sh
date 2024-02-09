#!/usr/bin/env bash

export IMAGE=$1
export VERSION=$2
export DOCKER_USER=$3
export DOCKER_PASS=$4
echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
docker-compose -f docker-compose.yaml up --detach 
echo "success"
