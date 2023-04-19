package com.br.assistente.usuarios.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@Setter
public class Consultas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuarioid")
    private Integer usuarioId;

    @Column(name = "profissionalid")
    private Integer profissionalId;

    @Column(name = "horaconsulta")
    private LocalDateTime dataHoraConsulta;
}
