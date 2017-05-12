CREATE DATABASE wft_phonebook;

USE wft_phonebook;

CREATE TABLE IF NOT EXISTS contacts(
  id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  firstname NVARCHAR(30),
  lastname NVARCHAR(30),
  phonenumber NVARCHAR(30)
) ENGINE InnoDB;


INSERT INTO contacts(firstname,lastname,phonenumber) VALUES("Adolf","Hitler","999999999");
INSERT INTO contacts(firstname,lastname,phonenumber) VALUES("Iosif","Stalin","555555555");
INSERT INTO contacts(firstname,lastname,phonenumber) VALUES("Vaxarshak","Grigoryan","898799898");
INSERT INTO contacts(firstname,lastname,phonenumber) VALUES("Valodik","Valodikyan","7898446546");
INSERT INTO contacts(firstname,lastname,phonenumber) VALUES("Poghos","Baghdasaryan","789463213");

