#!/usr/bin/env bash

export IMAGE=$1
export VERSION=$2
docker-compose -f docker-compose.yaml up --detach 
echo "success"
export TEST_=test_