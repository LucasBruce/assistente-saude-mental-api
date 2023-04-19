 drop table if exists consultas;
CREATE TABLE consultas(
    id serial primary key,
    usuarioid int not null,
    profissionalid int not null,
    horaconsulta timestamp,
    constraint fk_usuario_consulta FOREIGN key(usuarioid) REFERENCES usuarios(id),
    constraint fk_profissionais_consulta FOREIGN key(profissionalid) REFERENCES profissionais(id)
);

CREATE SEQUENCE profissionais_id_sequence START 1;
