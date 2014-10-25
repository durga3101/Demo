#!/bin/bash

#printf "\n" | java -cp "lib/main/*:bin/main/" -XX:MaxPermSize=256M com.trailblazers.freewheelers.FreeWheelersServer > server.log &

printf "\n" | java -XX:MaxPermSize=256M -jar jetty-runner-8.1.14.v20131031.jar --port 9090 --log freewheelers.log freewheelers.war > jetty.server &