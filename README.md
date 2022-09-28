Welcome to the Bike-tracking and visualizing App (Backend)
==========

The main goal of this Project is that I can train some techniques from Spring Boot to a React Frontend.
I don't think this will be a big thing, but I try to build it fullstack.

## Table of Contents

- [Introduction](#Introduction)
- [Tech stack](#Tech stack)
- [Versions](#Versions)
- [Build](#Build)
- [Run](#Run)
- [API](#API)

## Introduction

In this App it is possible to save data about your bicycle movements, like driven kilometers, movement time, average
speed and maximum speed. The App will provide you with a table-view of your monthly or yearly movements with
the overall kilometers and the current top values for average and maximum speed.<br>
It will also be possible to save your costs on parts and maintenance and calculate a costs per kilometer
information.<br>
I will try to visualize some things in charts.

## Tech stack

This project is made with <b>Java</b> and <b>Spring Boot</b> to provide a backend with <b>REST API</b>.
The backend uses a <b>PostgreSQL</b> database for persistence which is called with <b>Spring Data JPA</b>.
I also use <b>Flyway</b> to do my custom database migration to keep the full control by my own.
After that I used some little helpers like <b>lombok</b> to reduce the overhead of writing the same code over and over
again and <b>javafaker</b> to create the data for my integration tests.

## Versions

### 0.0.1

In the first Version it will be possible to register an owner, adding bikes, record driven kilometers and persist them
in a database.

## Build

JDK 17.0.1 or higher is recommended.
To build the application right now you can simply use `mvn clean package`

## Run

JDK 17.0.1 or higher is recommended.
Currently, it is recommended to run the application over IDE.<br>
In all cases you need to configure a PostgreSQL database first.

### PostgreSQL Docker Container

I highly recommend using a postgres docker container as database in development mode.
When you have installed docker and compose on your system you can run the docker-compose.yml
to automatically configure the needed container by simply execute `docker compose up`.
Your volume will be saved locally to `\\wsl$\docker-desktop-data\version-pack-data\community\docker\volumes`.
You can stop the test database setup with `docker compose down`. If you also want to delete the volume just
append `--volumes`

If it already exists, and it is not up and running, you can simply start it via `docker start bt-test`

To psql into the postgres container use `./psql-into-database.bat` from bin/ directory.

### Initialize Database

Docker compose will bring up a test cluster and auto-generate the needed database, user and password.
Flyway will perform all commands provided in `/src/main/resources/db/migration` on application start up.

## API

The backend provides you a REST-API that can be reached on the base-URL `localhost:8080/api/v1/` if you are running
locally.

### /owners

- /all <b>GET (no Parameters)</b><br>
  Returns all owners that are persisted in the Database.

- /{id} <b>GET (id Parameter)</b><br>
  Returns one single owner with given id or null.

- /create <b>POST (Body: Owner)</b><br>
  You can perform a POST request against owners with a body containing `{ "firstName": "Foo", "lastName": "Bar" }` to
  add a new owner to the database.<br>
  By adding `"bikes": [ { "name":"bla", "maker":"blub", "model":"NX-01", "bikeType":"OTHER"}, ... ]` to your owner you
  will automatically create a corresponding bike in your database for given owner.

- /delete/{id} <b>DELETE (id Parameter)</b><br>
  Deletes Entry with given id. Entry must exist.

- /update/{id} <b>PATCH (id Parameter, Body: Owner)</b><br>
  Updates owner with given id and input from message body. Body can be used similar to POST.

## TODOs

- Implement Tour Repository
- Integration Test Movements and Tours
- For using views make the Entity @Immutable and use a Read-only Repository

> @NoRepositoryBean<br>
> public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {<br>
> List<T> findAll();<br>
> List<T> findAll(Sort sort);<br>
> Page<T> findAll(Pageable pageable);<br>
> Optional<T> findById(ID id);<br>
> long count();<br>
> }
