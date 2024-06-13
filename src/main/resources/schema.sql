DROP TABLE if exists cars cascade ;
DROP TABLE if exists users  cascade ;
DROP TABLE if exists bookings  cascade ;
DROP TABLE if exists user_documents  cascade ;
DROP TABLE if exists users_favorites  cascade ;
DROP TABLE if exists fines  cascade ;
DROP TABLE if exists roles  cascade ;
DROP TABLE if exists payments  cascade ;
DROP TABLE if exists users_roles  cascade ;
DROP TABLE if exists brands  cascade ;
DROP TABLE if exists colors  cascade ;
DROP TABLE if exists engine_type  cascade ;
drop table if exists user_addresses cascade ;
DROP TABLE if exists model  cascade ;

DROP TABLE if exists colors  cascade ;
DROP TABLE if exists engine_type  cascade ;
drop table if exists user_addresses cascade ;




-- Создание таблицы cars

create table user_addresses (
    id serial primary key,
    zipcode integer,
    city varchar(200),
    street varchar (200),
    house varchar(100),
    apartment varchar(100)
);
-- Создание таблицы users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       phone VARCHAR(20) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       address integer references user_addresses(id),
                       isActive bool not null default true,
                       balance DECIMAL default 0
);

create table brands(
                       id serial primary key,
                       name varchar(50)
);

create table colors (
                        id serial primary key,
                        name varchar(50)
);


create table engine_type  (
                              id serial primary key,
                              name varchar(50)
);

create table model  (
                              id serial primary key,
                              name varchar(50)
);


CREATE TABLE cars (
                      id SERIAL PRIMARY KEY,
                      vin VARCHAR(17) NOT NULL unique,
                      brand bigint NOT NULL references brands(id), --brand
                      model bigint NOT NULL references model(id),
                      power integer not null, -- мощность в Кв
                      engine_type bigint not null references engine_type(id),
                      year INT NOT NULL,    --
                      registration_number VARCHAR(20) NOT NULL,
                      color bigint not null references colors(id),
                      status VARCHAR(20) NOT NULL,
                      price integer not null ,
                      photo VARCHAR
);

create table rr_account(
    id serial,
    balance bigint
);

-- Создание таблицы payment_documents
CREATE TABLE payments (
                          id bigserial PRIMARY KEY,
                          user_id INT NOT NULL,
                          payment_summ DECIMAL,
                          document_type VARCHAR(50) NOT NULL,
                          document_number VARCHAR(50) NOT NULL,
                          transaction_datetime TIMESTAMP NOT NULL,
                          send_to integer references rr_account(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Создание таблицы bookings
CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          car_id INT NOT NULL,
                          user_id INT NOT NULL,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          total_amount DECIMAL(10,2) NOT NULL,
                          payment bigint references payments(id),
                          status VARCHAR(20) NOT NULL,
                          price integer not null ,
                          comments VARCHAR(5000),
                          FOREIGN KEY (car_id) REFERENCES cars(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Создание таблицы renter_documents
CREATE TABLE user_documents (
                                id SERIAL PRIMARY KEY,
                                user_id INT NOT NULL,
                                first_name VARCHAR(70) NOT NULL,
                                last_name VARCHAR(100) NOT NULL,
                                document_type VARCHAR(50) NOT NULL,
                                document_number VARCHAR(50) UNIQUE NOT NULL,
                                issue VARCHAR(250) NOT NULL,
                                expiry_date DATE NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES users(id)
);



-- Создание таблицы users_favorites
CREATE TABLE users_favorites (
                                 user_id BIGINT NOT NULL,
                                 car_id BIGINT NOT NULL,
                                 PRIMARY KEY (user_id, car_id),
                                 FOREIGN KEY (user_id) REFERENCES users(id),
                                 FOREIGN KEY (car_id) REFERENCES cars(id)
);

-- Создание таблицы fines
CREATE TABLE fines (
                       id SERIAL PRIMARY KEY,
                       car_id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       date DATE,
                       summ DECIMAL,
                       registration_number VARCHAR(10),
                       payment bigint references payments(id),
                       FOREIGN KEY (car_id) REFERENCES cars(id),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Создание таблицы roles
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- Создание промежуточной таблицы user_roles для связи ManyToMany
CREATE TABLE users_roles (
                             user_id INT NOT NULL,
                             role_id INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (role_id) REFERENCES roles(id)
);





