INSERT INTO account (email_address, account_name, password, phone_number, enabled)
       VALUES ('admin@freewheelers.com', 'AdminCat','admin', '', true);
INSERT INTO account (email_address, account_name, password, phone_number, enabled)
       VALUES ('user@freewheelers.com', 'UserCat','user', '', true);

INSERT INTO account_role (account_name, role) VALUES ('AdminCat', 'ROLE_ADMIN');
INSERT INTO account_role (account_name, role) VALUES ('UserCat', 'ROLE_USER');

INSERT INTO item_type (name) VALUES ('Frames');
INSERT INTO item_type (name) VALUES ('Accessories');
