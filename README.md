## In PSQL: 

CREATE DATABASE hair_salon;   
\c hair_salon;     
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);   
CREATE TABLE clients (id serial PRIMARY KEY, client_name varchar, phone varchar, stylist_id int);   
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;    

## (Alternative) With hair_salon.sql:

(in psql): “CREATE DATABASE hair_salon;”      
(in bash): “psql hair_salon < hair_salon.sql”     
(in psql): “CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;”     
 
