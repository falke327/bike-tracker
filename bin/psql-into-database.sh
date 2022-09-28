#!/bin/bash

docker run -it --rm --network=bike-tracker_bt-db postgres:alpine psql -h bt-test -U bike_tracker
# - runs a container in interactive mode
# - removes container after use
# - using the docker network bike-tracker_bt-db
# - uses latest postgres:alpine image
# - starts psql command on host container bt-test
# - with user bike_tracker