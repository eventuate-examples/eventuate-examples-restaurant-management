# /bin/bash

if [ -z "$EVENTUATE_LOCAL" ] && [ -z "$EVENTUATE_API_KEY_ID" -o -z "$EVENTUATE_API_KEY_SECRET" ] ; then
  echo You must set EVENTUATE_API_KEY_ID and  EVENTUATE_API_KEY_SECRET
  exit -1
fi

set -e

if [ -z "$SERVICE_HOST" ] ; then
  if which docker-machine >/dev/null; then
    export SERVICE_HOST=$(docker-machine ip default)
  else
    export SERVICE_HOST=localhost
 fi
 echo set SERVICE_HOST $SERVICE_HOST
fi

if [ -z "$SPRING_REDIS_HOST" ] ; then
  if which docker-machine >/dev/null; then
    export SPRING_REDIS_HOST=$(docker-machine ip default)
  else
    export SPRING_REDIS_HOST=localhost
 fi
 echo set SPRING_REDIS_HOST $SPRING_REDIS_HOST
fi

DOCKER_COMPOSE="docker-compose  "

if [ "$1" = "-f" ] ; then
  shift;
  DOCKER_COMPOSE="$DOCKER_COMPOSE -f ${1?}"
  shift
fi

if [ "$1" = "--use-existing" ] ; then
  shift;
else
  ${DOCKER_COMPOSE?} stop
  ${DOCKER_COMPOSE?} rm -v --force
fi

NO_RM=false

if [ "$1" = "--no-rm" ] ; then
  NO_RM=true
  shift
fi

${DOCKER_COMPOSE?} up -d redis $EXTRA_INFRASTRUCTURE_SERVICES

./gradlew $* clean build -x :e2e-test:test

${DOCKER_COMPOSE?} up -d

./wait-for-services.sh $SERVICE_HOST 8081 8082

set -e

./gradlew -a $* :e2e-test:cleanTest :e2e-test:test -P ignoreE2EFailures=false

if [ $NO_RM = false ] ; then
  ${DOCKER_COMPOSE?} stop
  ${DOCKER_COMPOSE?} rm -v --force
fi
