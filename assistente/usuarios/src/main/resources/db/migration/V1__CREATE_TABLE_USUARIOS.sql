CREATE TABLE usuarios(
    id serial primary key,
    username VARCHAR(200) NOT NULL,
    nome VARCHAR(400) NOT NULL,
    email VARCHAR(200) NOT NULL,
    senha VARCHAR(200) NOT NULL
);

CREATE SEQUENCE usuario_id_sequence START 1;