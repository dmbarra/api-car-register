DROP SCHEMA IF EXISTS 'car_schema';
CREATE SCHEMA 'car_schema';

CREATE TABLE 'car_schema'.'cars' (
  id                  SERIAL PRIMARY KEY,
  model               VARCHAR,
  year                VARCHAR,
  collor              VARCHAR,
  category            VARCHAR
);

INSERT INTO 'car_schema'.'cars' VALUES ();
