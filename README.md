
# Wildlife Tracker App
Version 0.0

## Description
Application to track and manage wildlife in park to help the rangers and animal conservation agencies to easily track animals. The Application used  Java, PostgreSQL, and Spark with JUnit tests and RESTful routing.

## Details
### User Stories:
* As a salon ranger, I need to be able to see a list of all our animals.
* As ranger/wildlife service center, I need to be able to select a sighting/animal, see their details, and edit them.
* As a wildlife service centre, I need to view all ranger associated with a sighting.

* As a ranger, I need to be able to add new animal and a sighing for that particular animal.
* As a ranger, I need to able to sign up/login to add a sighting.


## Setup and Installation
* Clone directory
* Setup database in PSQL:
* * CREATE DATABASE wildlife_tracker;
* * \c wildlife_tracker
* * CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar, health varchar, age int);
* * CREATE TABLE sightings (id serial PRIMARY KEY, location varchar,  ranger_name varchar);
* * CREATE TABLE sightings(id serial PRIMARY KEY, animalId int, location varchar, rangername varchar);


### Author
Dickson
[email](dicksom6@gmail.com) dicksonm6@gmail.com

## Links
Link to [github  repository](https://github.com/Dicksonmuli/wildlife-tracker)
Link to [deployed website](http://wildlife-tracker-app.herokuapp.com/)

## Technologies Used
* Java,
* JUnit,
* Spark,
* PostgreSQL,
* Gradle
* Velocity Template Engine

## Legal


Copyright (c) 2017 Copyright Dickson Muli All Rights Reserved.
