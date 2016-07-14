-- // create_shippingaddress
-- Migration SQL that makes the change goes here.
CREATE TABLE shipping_address
(
      shipping_address_id SERIAL PRIMARY KEY,
      account_id NUMERIC NOT NULL,
      street_1 CHARACTER VARYING(255) NOT NULL,
      street_2 CHARACTER VARYING(255) NOT NULL UNIQUE,
      city CHARACTER VARYING(255) NOT NULL,
      state CHARACTER VARYING(32) NOT NULL,
      postcode CHARACTER VARYING(32) NOT NULL,
      country CHARACTER VARYING(32) NOT NULL,
      enabled BOOLEAN NOT NULL
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE shipping_address;


