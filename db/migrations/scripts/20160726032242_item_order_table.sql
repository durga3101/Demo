-- // item_order_table
-- Migration SQL that makes the change goes here.

CREATE TABLE item_order (
      order_id BIGINT NOT NULL,
      item_id BIGINT NOT NULL
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE item_order

