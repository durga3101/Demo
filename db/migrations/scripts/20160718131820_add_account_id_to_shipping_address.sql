-- // add_account_id_to_shipping_address_table
-- Migration SQL that makes the change goes here.
ALTER TABLE shipping_address
ADD account_id BIGINT;

-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE shipping_address
DROP account_id BIGINT;