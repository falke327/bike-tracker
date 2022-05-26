CREATE TABLE bike_tracker.owner (
    id          serial          NOT NULL,
    first_name  varchar(100)    NOT NULL,
    last_name   varchar(100)    NOT NULL,
    CONSTRAINT pk_owner PRIMARY KEY (id)
);

CREATE TYPE bike_tracker."bike_type" AS ENUM (
    'Trekking',
    'Race',
    'City',
    'MTB',
    'Children'
);

CREATE TABLE bike_tracker.bike (
    id          serial                      NOT NULL,
    bike_owner  integer                     NOT NULL,
    name        varchar(100)                NOT NULL,
    vendor      varchar(100)                NULL,
    model       varchar(100)                NULL,
    type        bike_tracker."bike_type"    NULL,
    CONSTRAINT pk_bike PRIMARY KEY (id),
    CONSTRAINT bike_bike_owner_fkey FOREIGN KEY (bike_owner) REFERENCES bike_tracker."owner"(id)
);

CREATE TABLE bike_tracker.costs (
    id              serial          NOT NULL,
    value_date      date            NOT NULL,
    description     varchar(255)    NOT NULL,
    price           numeric(10, 2)  NOT NULL,
    bike            integer         NOT NULL,
    CONSTRAINT pk_costs PRIMARY KEY (id, value_date),
    CONSTRAINT costs_bike_fkey FOREIGN KEY (bike) REFERENCES bike_tracker.bike(id)
);

CREATE TABLE bike_tracker.tours (
    id              serial          NOT NULL,
    tour_date       date            NOT NULL,
    distance        numeric(10, 2)  NOT NULL,
    description     varchar(100)    NOT NULL,
    duration        integer         NULL,
    v_max           numeric(5, 2)   NULL,
    info            varchar(255)    NULL,
    CONSTRAINT pk_tours PRIMARY KEY (id)
);

CREATE TABLE bike_tracker.movements (
    bike    integer     NOT NULL,
    tour    integer     NOT NULL,
    CONSTRAINT pk_movements PRIMARY KEY (bike, tour),
    CONSTRAINT movements_bike_fkey FOREIGN KEY (bike) REFERENCES bike_tracker.bike(id),
    CONSTRAINT movements_tour_fkey FOREIGN KEY (tour) REFERENCES bike_tracker.tours(id)
);
