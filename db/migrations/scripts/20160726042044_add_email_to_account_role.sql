-- // add email to account role
-- Migration SQL that makes the change goes here.

ALTER TABLE account_role ADD email_address CHARACTER VARYING(255);

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE account_role
DROP email_address CHARACTER VARYING(255);

