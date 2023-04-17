 drop table if exists metausuarios;

 CREATE TABLE metausuarios (
    id SERIAL primary key,
    usuarioid integer not null,
    metaid integer not null,
    metafinalizada boolean default false,
    dataMeta timestamp NOT null,
    constraint fk_usuario FOREIGN key(usuarioid) REFERENCES usuarios(id),
    constraint fk_meta FOREIGN key(metaid) REFERENCES metas(id)
);

CREATE SEQUENCE meta_usuarios_id_sequence START 1;




