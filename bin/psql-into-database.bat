docker run -it --rm --network=bt-db postgres:alpine psql -h bt-test -U postgres
:: - runs a container in interactive mode
:: - removes container after use
:: - using the docker network fc-db
:: - uses latest postgres:alpine image
:: - starts psql command on host container fc-dev
:: - with user postgres