#!/usr/bin/env bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CUSTOMIZATION=$JBOSS_HOME/customization
JBOSS_STANDALONE_CONFIG=$JBOSS_HOME/standalone/configuration/
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}


function wait_for_server() {
  until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
  done
}

echo "=> Starting WildFly server"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &

echo "=> Waiting for the server to boot"
wait_for_server

echo "=====> Download Postgresql JDBC driver"
curl --location --output $(pwd)/postgresql-42.0.0.jar --url https://jdbc.postgresql.org/download/postgresql-42.0.0.jar

echo $(pwd)
echo "=====> Executing the commands"
$JBOSS_CLI -c --file=`dirname "$0"`/commands.cli

echo "Add Administration wildfly user"
/opt/jboss/wildfly/bin/add-user.sh admin admin


echo "=====> Keycloak Adapper"



echo "====> Shutting down WildFly"
if [ "$JBOSS_MODE" = "standalone" ]; then
  $JBOSS_CLI -c ":shutdown"
else
  $JBOSS_CLI -c "/host=*:shutdown"
fi