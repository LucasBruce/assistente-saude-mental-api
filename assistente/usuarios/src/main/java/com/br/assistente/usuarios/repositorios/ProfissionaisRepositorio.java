package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import com.br.assistente.usuarios.entidades.Profissionais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProfissionaisRepositorio extends PagingAndSortingRepository<Profissionais, Integer>, CrudRepository<Profissionais, Integer> {
    List<Profissionais> findByEspecialidade(String especialidade);
}
