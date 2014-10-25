#!/bin/bash

set -e

USER=$1
HOST=$2

if [ $# -ne 2 ]; then
  echo usage: scripts/install.sh USER HOST
  exit -1
fi;

if [ ! -e "dist/freewheelers.war" ]; then
  echo "cannot find dist/freewheelers.war to deploy"
  exit -1
fi;

scp dist/* ${USER}@${HOST}:/tmp

#ssh ${USER}@${HOST} /bin/bash << EOF
#TIMESTAMP=\$(date +"%Y-%m-%d-%HH%MM%Ss")
#mkdir -p /tmp/\$TIMESTAMP
#mv /tmp/freewheelers.war /tmp/\$TIMESTAMP
#mv /tmp/jetty-runner-8.1.14.v20131031.jar /tmp/\$TIMESTAMP
#cd /tmp/\$TIMESTAMP
#sh stop-server.sh
#sh db/migrations/mybatis/bin/migrate --path=./db/migrations up
#nohup sh start-server.sh > server.out 2> server.err < /dev/null
#EOF

