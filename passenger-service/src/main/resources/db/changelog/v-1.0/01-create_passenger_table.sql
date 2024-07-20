CREATE TABLE passenger (
                           id BIGINT PRIMARY KEY generated always as identity,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE
);
