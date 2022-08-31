Welcome to the Bike-tracking and visualizing App (Backend)
==========

The main goal of this Project is that I can train some techniques from Spring Boot to a React Frontend.
I don't think this will be a big thing, but I try to build it fullstack.

## Table of Contents

- [Introduction](#Introduction)
- [Versions](#Versions)
- [Build](#Build)
- [Run](#Run)
- [API](#API)

## Introduction

In this App it is possible to save data about your bicycle movements, like driven kilometers, movement time, average
speed and maximum speed. The App will provide you with a table-view of your monthly or yearly movements with
the overall Kilometers and the current Top values for average and maximum speed.<br>
It will also be possible to save your costs on parts and maintenance and calculate a costs per kilometer
information.<br>
I will try to visualize some things in charts.

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

[//]: # (TODO: Provide Docker compose file)
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
  add a new owner to the database.

- /delete/{id} <b>GET (id Parameter)</b><br>
  Deletes Entry with given id. Entry must exist.

- /update/{id} <b>PATCH (id Parameter, Body: Owner)</b><br>
  Updates owner with given id and input from message body.

## TODOs

- fix owner update with bike
- also see https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
- for integration-testing see https://www.baeldung.com/integration-testing-a-rest-api
- and https://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services

- For using views make the Entity @Immutable and use a Read-only Repository
> @NoRepositoryBean<br>
> public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {<br>
>    List<T> findAll();<br>
>    List<T> findAll(Sort sort);<br>
>    Page<T> findAll(Pageable pageable);<br>
>    Optional<T> findById(ID id);<br>
>    long count();<br>
> }

- DB cleanup for testing
Annotate Class with
> @RunWith(CamelSpringRunner.class)<br>
> @BootstrapWith(CamelTestContextBootstrapper.class)<br>
> @ContextConfiguration(locations = { "classpath:/test-context.xml", "classpath:/test-jdbc-context.xml" })<br>
> @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class })<br>
> @DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)<br>
> @UseAdviceWith<br>

Write resources/test-jdbc-context.xml

Something like that:
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
> xmlns:util="http://www.springframework.org/schema/util" xmlns:c="http://www.springframework.org/schema/c"
> xsi:schemaLocation="
> http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
> http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
> http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
> ">
>  <bean id="adminOnDEV" parent="defaultDS">
>    <property name="driverClass" value="org.postgresql.Driver" />
>    <property name="jdbcUrl" value="jdbc:postgresql://${db-host}:${db-port}/${db-name}" />
>    <property name="user" value="ADMIN" />
>    <property name="password" value="${dev-admin-password}" />
>    <property name="preferredTestQuery" value="select 1" />
>  </bean>
> </beans>

> @Autowired<br>
> @Qualifier("fooDS")<br>
> protected DataSource dataSource;<br>
> <br>
> @Autowired<br>
> @Qualifier("adminOnDEV")<br>
> protected DataSource dataSourceAdminDEV;<br>
> protected JdbcTemplate jdbcTemplate;<br>
> protected JdbcTemplate jdbcTemplateAdmin;<br>
> @Before<br>
> public void setUpCommon() throws Exception {<br>
> jdbcTemplate = new JdbcTemplate(dataSource);<br>
> jdbcTemplateAdmin = new JdbcTemplate(dataSourceAdminDEV);<br>
> initDb();<br>
> }<br>
> @After<br>
> public void cleanUp() {<br>
> jdbcTemplate.execute("DELETE FROM XYZ");<br>
> jdbcTemplate.execute("DELETE FROM ASD");<br>
> jdbcTemplateAdmin.execute("TRUNCATE TABLE FOO.XYZ");<br>
> }<br>
