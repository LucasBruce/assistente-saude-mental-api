package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.Usuarios;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuariosRepositorio extends PagingAndSortingRepository<Usuarios, Integer> {
}
