-- // create_country
-- Migration SQL that makes the change goes here.
CREATE TABLE country(
      country_id SERIAL PRIMARY KEY,
      country_name CHARACTER VARYING(255) NOT NULL,
      vat_rate NUMERIC(19,2),
      duty_rate NUMERIC(19,2)
);


-- //@UNDO
DROP TABLE country;
-- SQL to undo the change goes here.


