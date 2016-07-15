-- // insert_countries

INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('UK',20,0 );
INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('ITALY',21,0 );
INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('CANADA',0,9 );
INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('USA',0,5.4 );
INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('GERMANY',19,0);
INSERT INTO country (country_name, vat_rate, duty_rate)VALUES ('FRANCE',19.6,0);
-- Migration SQL that makes the change goes here.



-- //@UNDO
-- SQL to undo the change goes here.
DELETE * FROM country;


