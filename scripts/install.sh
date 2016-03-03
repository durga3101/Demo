#!/bin/bash

set -e

USER=$1
HOST=$2

if [ $# -ne 2 ]; then
  echo usage: scripts/install.sh USER HOST
  exit -1
fi;

if [ ! -e "dist/freewheelers.zip" ]; then
  echo "cannot find dist/freewheelers.zip to deploy"
  exit -1
fi;

type -p java > /dev/null || (echo "java not found" && exit -1)

scp dist/freewheelers.zip ${USER}@${HOST}:/tmp

ssh ${USER}@${HOST} /bin/bash << EOF

cd /tmp/
jetty_path=/tmp/jetty-runner-8.1.14.v20131031.jar
if ! [ -f \$jetty_path ];then
  curl -O http://central.maven.org/maven2/org/mortbay/jetty/jetty-runner/8.1.14.v20131031/jetty-runner-8.1.14.v20131031.jar
fi

#Create directory and move app
sudo su appuser
TIMESTAMP=\$(date +"%Y-%m-%d-%HH%MM%Ss")
mkdir -p /home/appuser/freewheelers/\$TIMESTAMP
mv /tmp/freewheelers.zip /home/appuser/freewheelers/\$TIMESTAMP
cd /home/appuser/freewheelers/\$TIMESTAMP
unzip freewheelers.zip
cp \$jetty_path .

#Stop app service
sudo sh /etc/init.d/freewheelers stop

#DB migrations
sh db/migrations/mybatis/bin/migrate --path=./db/migrations up

#Start app service
sudo cp scripts/freewheelers.init /etc/init.d/freewheelers
sudo chmod 0755 /etc/init.d/freewheelers
sudo chown root:root /etc/init.d/freewheelers
sudo sh /etc/init.d/freewheelers start
EOF

