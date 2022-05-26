@REM This file initializes a test database for you
docker --version
:: checks docker version

cd ..
mkdir test-db-data
cd test-db-data
:: create local directory to store the data for local development database

docker pull postgres:alpine
:: pulls latest postgres:alpine image

docker network create bt-db
:: creates docker network

docker run --name bt-test -p 55010:5432 --network=bt-db -v "%cd%:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:alpine
:: runs a docker container
:: - named fc-dev
:: - forwarding local port 51010 to container port 5432
:: - using the docker network fc-db
:: - mounts current folder as data volume into the container
:: - sets the environment variable for postgres password to password
:: - runs the container in detached mode
:: - uses latest postgres:alpine image

docker ps -a
:: shows if container is up and running