-- // item_image_url
-- Migration SQL that makes the change goes here.

ALTER TABLE item
ADD imageURL CHARACTER VARYING(255);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE item
DROP imageURL CHARACTER VARYING(255);
