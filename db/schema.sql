CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP
);

CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    name TEXT
);