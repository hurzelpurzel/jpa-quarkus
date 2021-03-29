/* Title,Author,Genre,Height,Publisher */

CREATE TABLE books(
   id INT NOT NULL AUTO_INCREMENT,
   Title VARCHAR(100) NOT NULL,
   Author VARCHAR(40) NOT NULL,
   Genre VARCHAR(40) NOT NULL,
   Height INT NOT NULL,
   Publisher VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id )
);