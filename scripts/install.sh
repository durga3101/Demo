#!/bin/bash
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

#Setup error handling
function errorHandler {
    echo "An error occured while installing the Freewheelers application"
    exit 1
}
trap errorHandler SIGINT SIGTERM ERR

#Start actual installation
type -p java > /dev/null || (echo "java not found" && exit -1)

scp dist/freewheelers.zip ${USER}@${HOST}:/tmp

ssh ${USER}@${HOST} /bin/bash << EOF

sudo su appuser

cd /tmp/
jetty_path=/tmp/jetty-runner-8.1.14.v20131031.jar
if ! [ -f \$jetty_path ];then
  curl -O http://central.maven.org/maven2/org/mortbay/jetty/jetty-runner/8.1.14.v20131031/jetty-runner-8.1.14.v20131031.jar
fi

#Create directory and move app
sudo chown appuser:user /tmp/freewheelers.zip
TIMESTAMP=\$(date +"%Y-%m-%d-%HH%MM%Ss")
<<<<<<< 9988d05027e7e6ddb796c73ec33ad99646960be8
rm -rf /home/appuser/freewheelers/
mkdir -p /home/appuser/freewheelers/\$TIMESTAMP || exit 1
mkdir -p /home/appuser/freewheelers/\$TIMESTAMP/work || exit 1
mv /tmp/freewheelers.zip /home/appuser/freewheelers/\$TIMESTAMP || exit 1
cd /home/appuser/freewheelers/\$TIMESTAMP
unzip freewheelers.zip || exit 1
cp \$jetty_path /home/appuser/freewheelers/\$TIMESTAMP || exit 1
ln -nfs /home/appuser/freewheelers/\$TIMESTAMP /home/appuser/freewheelers/current_version || exit 1

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
