-- // drop_reserve_order_id_from_order_table
-- Migration SQL that makes the change goes here.
ALTER TABLE order_table
DROP COLUMN reserve_order_id;


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE order_table
ADD reserve_order_id BIGINT NOT NULL;

