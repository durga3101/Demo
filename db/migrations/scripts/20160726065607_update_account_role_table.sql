-- // update account_role table
-- Migration SQL that makes the change goes here.

-- update account_role
-- set email_address = (
--   select account.email_address from account
--   where account_role.account_name = account.account_name limit 1)
-- where account_role.email_address is null;


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