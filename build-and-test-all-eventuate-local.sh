#! /bin/bash -e

export EXTRA_INFRASTRUCTURE_SERVICES=cdcservice
export EVENTUATE_LOCAL=yes
. ./set-env.sh
./_build-and-test-all.sh -f docker-compose-eventuate-local.yml $* -P eventuateDriver=local
