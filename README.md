CREATE DATABASE wildlife_tracker;
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar, health varchar, age int);

CREATE TABLE sightings (id serial PRIMARY KEY, location varchar,  ranger_name varchar);


DROP DATABASE wildlife_tracker_test ;

CREATE TABLE sightings(id serial PRIMARY KEY, animalId int, location varchar, rangername varchar)
