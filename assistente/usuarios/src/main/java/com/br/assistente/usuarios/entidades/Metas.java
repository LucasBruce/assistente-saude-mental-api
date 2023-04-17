package com.br.assistente.usuarios.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "metas")
@Getter
@Setter
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descricao;

    @Column
    private String tipoMeta;

    @ManyToMany
    @JoinTable(
            name = "metausuarios",
            joinColumns = {@JoinColumn(name = "usuarioid")},
            inverseJoinColumns = {@JoinColumn(name = "metaid")}
    )
    private Set<Usuarios> usuarios;

}
