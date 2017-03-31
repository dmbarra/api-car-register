CREATE SCHEMA "car_schema";

CREATE TABLE 'car_schema'.'cars' (
  id                  SERIAL PRIMARY KEY,
  model               VARCHAR,
  year                VARCHAR,
  collor              VARCHAR,
  category            VARCHAR
);

CREATE SEQUENCE car_register_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
