package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.Metas;
import com.br.assistente.usuarios.entidades.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetasRepositorio  extends PagingAndSortingRepository<Metas, Integer>, CrudRepository<Metas, Integer> {
}
