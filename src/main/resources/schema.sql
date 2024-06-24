-- DROP TABLE if exists cars cascade;
-- DROP TABLE if exists users cascade;
-- DROP TABLE if exists bookings cascade;
-- DROP TABLE if exists user_documents cascade;
-- DROP TABLE if exists users_favorites cascade;
-- DROP TABLE if exists fines cascade;
-- DROP TABLE if exists roles cascade;
-- DROP TABLE if exists payments cascade;
-- DROP TABLE if exists users_roles cascade;
-- DROP TABLE if exists brands cascade;
-- DROP TABLE if exists colors cascade;
-- DROP TABLE if exists engine_type cascade;
-- drop table if exists user_addresses cascade;
-- DROP TABLE if exists models cascade;
-- DROP TABLE if exists rr_account cascade;
-- DROP TABLE if exists cards cascade;
-- DROP TABLE if exists car_statuses cascade;
-- DROP TABLE if exists booking_statuses cascade;
-- DROP TABLE if exists document_types cascade;
-- DROP TABLE if exists account cascade;
-- DROP TABLE if exists engine_types cascade;
-- DROP TABLE if exists model cascade;
--
--
-- DROP TABLE if exists colors cascade;
--
-- drop table if exists user_addresses cascade;


-- Создание таблицы cars

create table user_addresses
(
    id        serial primary key,
    zipcode   bigint,
    city      varchar(200) not null,
    street    varchar(200) not null,
    house     varchar(100) not null,
    apartment varchar(100) not null
);

create table car_statuses
(
    id   bigserial primary key,
    name varchar(20) not null
);

create table booking_statuses
(
    id   bigserial primary key,
    name varchar(20) not null
);

CREATE TABLE users
(
    id         bigserial PRIMARY KEY,
    first_name VARCHAR(50)         NOT NULL,
    last_name  VARCHAR(50)         NOT NULL,
    phone      VARCHAR(20)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    address_id bigint references user_addresses (id),
    isActive   bool                not null default true
);

create table cards
(
    id          bigserial primary key,
    number      bigint                       not null unique,
    owner       varchar                      not null,
    expire_date varchar                      not null,
    user_id     bigint references users (id) not null
);

-- Создание таблицы users


create table brands
(
    id   bigserial primary key,
    name varchar(50) not null
);

create table colors
(
    id   bigserial primary key,
    name varchar(50) not null
);


create table engine_types
(
    id   bigserial primary key,
    name varchar(50) not null
);

create table models
(
    id   bigserial primary key,
    name varchar(50) not null
);


CREATE TABLE cars
(
    id                  bigserial PRIMARY KEY,
    vin                 VARCHAR(17) NOT NULL unique,
    brand_id            bigint      NOT NULL references brands (id), --brand
    model_id            bigint      NOT NULL references models (id),
    power               bigint      not null,                        -- мощность в Кв
    engine_type_id      bigint      not null references engine_types (id),
    year                integer     NOT NULL,                        --
    registration_number VARCHAR(20) NOT NULL,
    color_id            bigint      not null references colors (id),
    status_id           integer     not null references car_statuses (id),
    price               integer     not null,
    photo               VARCHAR
);

-- Создание таблицы payment_documents
CREATE TABLE payments
(
    id                   bigserial PRIMARY KEY,
    from_sender          bigint references cards (id) not null,
    to_getter            bigint references cards (id) not null,
    payment_summ         decimal                      not null,
    transaction_datetime TIMESTAMP                    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы bookings
CREATE TABLE bookings
(
    id           bigserial PRIMARY KEY,
    car_id       bigint         NOT NULL,
    user_id      bigint         NOT NULL,
    start_date   DATE           NOT NULL,
    end_date     DATE           NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_id   bigint references payments (id),
    status_id    integer        NOT NULL references booking_statuses (id),
    comments     VARCHAR(5000),
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table document_types
(
    id   bigserial primary key,
    name varchar(50) not null
);

-- Создание таблицы renter_documents
CREATE TABLE user_documents
(
    id               bigserial PRIMARY KEY,
    user_id          bigint                                 NOT NULL,
    first_name       VARCHAR(70)                            NOT NULL,
    last_name        VARCHAR(100)                           NOT NULL,
    document_type_id integer references document_types (id) not null,
    document_number  integer                                not null,
    issue            VARCHAR(250)                           NOT NULL,
    expiry_date      DATE                                   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);


-- Создание таблицы users_favorites
CREATE TABLE users_favorites
(
    user_id BIGINT NOT NULL,
    car_id  BIGINT NOT NULL,
    PRIMARY KEY (user_id, car_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (car_id) REFERENCES cars (id)
);

-- Создание таблицы fines
CREATE TABLE fines
(
    id                  bigserial PRIMARY KEY,
    car_id              BIGINT       NOT NULL,
    user_id             BIGINT       NOT NULL,
    date                DATE         not null,
    summ                DECIMAL      not null,
    registration_number VARCHAR(100) not null,
    payment_id          bigint       not null references payments (id),
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы roles
CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Создание промежуточной таблицы user_roles для связи ManyToMany
CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);





