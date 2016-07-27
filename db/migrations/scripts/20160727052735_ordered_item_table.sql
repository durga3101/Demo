-- // ordered_item_table
-- Migration SQL that makes the change goes here.
CREATE TABLE ordered_items (
   order_id BIGINT NOT NULL,
   item_id BIGINT NOT NULL,
   quantity BIGINT NOT NULL
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE ordered_items;

