package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.Consultas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConsultaRepositorio extends PagingAndSortingRepository<Consultas, Integer>, CrudRepository<Consultas, Integer> {


    List<Consultas> findByUsuarioId(Integer usuarioId);
    List<Consultas> findByProfissionalId(Integer profissionalId);
}
