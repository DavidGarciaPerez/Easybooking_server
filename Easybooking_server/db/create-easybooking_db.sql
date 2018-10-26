DROP SCHEMA IF EXISTS easybooking_db;
DROP USER IF EXISTS 'daaaj_user'@'localhost';

CREATE SCHEMA easybooking_db;
CREATE USER 'daaaj_user'@'localhost' IDENTIFIED BY 'daaaj_password';
GRANT ALL ON easybooking_db.* TO 'daaaj_user'@'localhost';