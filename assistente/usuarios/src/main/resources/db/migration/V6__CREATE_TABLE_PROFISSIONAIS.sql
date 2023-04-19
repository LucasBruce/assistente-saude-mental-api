CREATE TABLE profissionais(
    id serial primary key,
    especialidade VARCHAR(200) NOT NULL,
    registroProfissional VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(200) NOT NULL
);

CREATE SEQUENCE profissionais_id_sequence START 1;