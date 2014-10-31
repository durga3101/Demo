#!/bin/bash

printf "\n" | java -XX:MaxPermSize=256M -jar jetty-runner-8.1.14.v20131031.jar --port 8080 --log freewheelers.log freewheelers.war > server.log &