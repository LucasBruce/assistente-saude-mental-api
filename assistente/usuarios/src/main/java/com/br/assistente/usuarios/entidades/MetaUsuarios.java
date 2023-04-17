package com.br.assistente.usuarios.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "metasusuarios")
@Getter
@Setter
public class MetaUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "metaid")
    private Integer metaId;

    @Column(name = "usuarioid")
    private Integer usuarioId;

    @Column
    private Date dataMeta;




}
