#! /bin/bash -e

export EXTRA_INFRASTRUCTURE_SERVICES=mysqlbinloginfrastructure
export EVENTUATE_LOCAL=yes
. ./set-env.sh
./_build-and-test-all.sh $* -P eventuateDriver=local
