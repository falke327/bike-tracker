# Welcome to the Bike Evaluator tracking and visualizing App
The main goal of this Project is that I can train some techniques from Spring Boot to a React Frontend.
I don't think this will be a big thing, but I try to build it fullstack.

## Table of Contents
- [Introduction](#Introduction)
- [Versions](#Versions)
- [Build](#Build)
- [Run](#Run)

## Introduction
In this App it is possible to save data about your bicycle movements, like driven kilometers, movement time, average
speed and maximum speed. The App will provide you with a table-view of your weekly, monthly or yearly movements with 
the overall Kilometers and the current Top values for average and maximum speed.<br>
It will also be possible to save your costs on parts and maintenance and calculate a costs per kilometer information.<br>
I will try to visualize some things in charts.

## Versions

### 0.0.1
In the first Version it will be possible to register a Bike, record driven kilometers and persist them in a database.

## Build
JDK 17.0.1 or higher is recommended.
To build the application right now you can simply use `mvn clean package`

## Run
JDK 17.0.1 or higher is recommended.
Currently it is recomended to run the application over IDE.<br>
In all cases you need to configure a PostgreSQL database first. (Description is coming soon)

### PostgreSQL Docker Container
I highly recommend using a postgres docker container as database in development mode.
When you have installed docker on your system you can run `./init-test-database.bat` int the bin/ directory
to automatically configure the needed container.

If it already exists, and it is not up and running, you can simply start it via `docker start bt-test`

To psql into the postgres container use `./psql-into-database.bat` from bin/ directory.

### Initialize Database
Currently, it is not possible to create roles and databases with flyway and connect to it
in the same step. You need to execute the commands in `/src/main/resources/db/init` by hand before you
can leave all the other database operations to flyway.
You only need to do this on the first time you are going to use the database.

Flyway will perform all commands provided in `/src/main/resources/db/migration` on application start up.
