CREATE TABLE metas(
    id serial primary key,
    descricao VARCHAR(200) NOT NULL,
    tipoMeta VARCHAR(400)
);

CREATE SEQUENCE meta_id_sequence START 1;