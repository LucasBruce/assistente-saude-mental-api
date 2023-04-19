package com.br.assistente.usuarios.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profissionais")
@Getter
@Setter
public class Profissionais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "registroprofissional")
    private String registroProfissional;

    @Column(name = "nome")
    private String nome;
}
