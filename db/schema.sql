DROP TABLE posts;
DROP TABLE candidates;
DROP TABLE cities;
DROP TABLE users;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP
);

CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id INTEGER,
    CONSTRAINT fk_cities
      FOREIGN KEY (city_id)
	  REFERENCES cities(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    pass TEXT
);