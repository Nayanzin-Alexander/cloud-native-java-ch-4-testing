#!/usr/bin/env bash

mvn clean install

cd ./user-service/

mvn spring-boot:run -Dspring.datasource.platform=e2e &

cd ././account-service/

mvn spring-boot:run -Dspring.datasource.platform=e2e