-- // update account_role table
-- Migration SQL that makes the change goes here.

update account_role
set email_address = (
  select account.email_address from account
  where account_role.account_name = account.account_name limit 1)
where account_role.email_address is null;

-- //@UNDO
-- SQL to undo the change goes here.