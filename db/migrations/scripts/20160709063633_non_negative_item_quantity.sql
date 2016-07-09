-- // non_negative_item_quantity
-- Migration SQL that makes the change goes here.
ALTER TABLE item
ADD CONSTRAINT item_testcol_non_negative
CHECK (quantity >= 0);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE item
DROP CONSTRAINT item_testcol_non_negative;

