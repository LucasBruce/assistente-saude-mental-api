 drop table if exists token;

 CREATE TABLE token (
    id SERIAL primary key,
    token varchar(200) NOT NULL UNIQUE,
    tipotoken varchar(200) NOT NULL UNIQUE
);

CREATE SEQUENCE token_id_sequence START 1;




