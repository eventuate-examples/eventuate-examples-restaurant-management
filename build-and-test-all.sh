# /bin/bash

if [ -z "$EVENTUATE_API_KEY_ID" -o -z "$EVENTUATE_API_KEY_SECRET" ] ; then
  echo You must set EVENTUATE_API_KEY_ID and  EVENTUATE_API_KEY_SECRET
  exit -1
fi

set -e
docker-compose stop
docker-compose rm -v --force

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

docker-compose up -d redis

./gradlew $* clean build -x :e2e-test:test

docker-compose up -d

./wait-for-services.sh $SERVICE_HOST 8081 8082

set -e

./gradlew -a $* :e2e-test:cleanTest :e2e-test:test -P ignoreE2EFailures=false

docker-compose stop
docker-compose rm -v --force
