-- // add coloum email to account_role
-- Migration SQL that makes the change goes here.

ALTER TABLE account_role
ADD email_address CHARACTER VARYING(255);

CREATE OR REPLACE FUNCTION migrateEmailFromAccount() RETURNS SETOF account AS $BODY$
DECLARE
 r account%rowtype;
BEGIN
FOR r IN SELECT * FROM account
LOOP
UPDATE account_role as a SET email_address  = r.email_address where a.role_id in (select b.role_id from account_role as b where b.account_name = r.account_name and b.email_address is NULL limit 1);
 RETURN NEXT r;
END LOOP;
RETURN;
END
$BODY$
LANGUAGE 'plpgsql' ;
select * from migrateEmailFromAccount();

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE account_role
DROP email_address CHARACTER VARYING(255);



