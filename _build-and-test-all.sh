# /bin/bash

if [ -z "$EVENTUATE_LOCAL" ] && [ -z "$EVENTUATE_API_KEY_ID" -o -z "$EVENTUATE_API_KEY_SECRET" ] ; then
  echo You must set EVENTUATE_API_KEY_ID and  EVENTUATE_API_KEY_SECRET
  exit -1
fi

set -e

dockerall="./gradlew mysqlbinlogCompose"
dockerinfrastructure="./gradlew ${EXTRA_INFRASTRUCTURE_SERVICES}Compose"

if [ -z "$SERVICE_HOST" ] ; then
  if [ -z "$DOCKER_HOST" ] ; then
    export SERVICE_HOST=`hostname`
  else
    echo using ${DOCKER_HOST?}
    XX=${DOCKER_HOST%\:*}
    export SERVICE_HOST=${XX#tcp\:\/\/}
  fi
  echo set SERVICE_HOST $SERVICE_HOST
fi

export SPRING_REDIS_HOST=$SERVICE_HOST
echo set SPRING_REDIS_HOST $SERVICE_HOST

if [ "$1" = "--use-existing" ] ; then
  shift;
else
  ${dockerall}Down
fi

NO_RM=false

if [ "$1" = "--no-rm" ] ; then
  NO_RM=true
  shift
fi

if [ ! -z "$EXTRA_INFRASTRUCTURE_SERVICES" ]; then
  ${dockerinfrastructure}Build
  ${dockerinfrastructure}Up
fi

./gradlew $* clean build -x :e2e-test:test

${dockerall}Build
${dockerall}Up

./wait-for-services.sh $SERVICE_HOST 8081 8082

set -e

./gradlew -a $* :e2e-test:cleanTest :e2e-test:test -P ignoreE2EFailures=false

if [ $NO_RM = false ] ; then
  ${dockerall}Down
fi
