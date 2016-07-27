-- // update country to UK for old users
-- Migration SQL that makes the change goes here.

update account
set country = 'UK'
where country is null;

-- //@UNDO
-- SQL to undo the change goes here.


