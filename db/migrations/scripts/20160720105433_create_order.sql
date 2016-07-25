-- // create_order
-- Migration SQL that makes the change goes here.

CREATE TABLE order_table
(
  order_id SERIAL PRIMARY KEY,
  account_id BIGINT NOT NULL,
  reserve_order_id BIGINT NOT NULL,
  status CHARACTER VARYING(255) NOT NULL,
  reservation_timestamp TIMESTAMP without time zone NOT NULL
);

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE order_table


