package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends PagingAndSortingRepository<Usuarios, Integer>, CrudRepository<Usuarios, Integer> {

}
