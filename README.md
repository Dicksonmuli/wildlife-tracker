CREATE DATABASE wildlife_tracker;
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

CREATE TABLE animals (id serial PRIMARY KEY, name varchar);

CREATE TABLE sightings (id serial PRIMARY KEY, location varchar,  ranger_name varchar);
