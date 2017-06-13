CREATE DATABASE administrateuser;
CREATE USER root WITH password 'root';
GRANT ALL ON DATABASE administrateuser TO root;

CREATE TABLE users (
  id            serial PRIMARY KEY,
  firstname     varchar(40) NOT NULL CHECK (firstName <> ''),
  lastname      varchar(40) NOT NULL CHECK (lastName <> ''),
  datebirthday  date ,
  login         varchar(40) NOT NULL CHECK (login <> ''),
  password      varchar(40) NOT NULL CHECK (password <> ''),
  aboutuser     text,
  residence     text
);

