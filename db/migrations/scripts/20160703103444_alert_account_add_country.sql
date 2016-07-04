-- // alert_account_add_country
-- Migration SQL that makes the change goes here.
ALTER TABLE account
ADD country CHARACTER VARYING(255);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE account
DROP country CHARACTER VARYING(255);

