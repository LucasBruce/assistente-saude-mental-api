package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import com.br.assistente.usuarios.entidades.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TokenRepositorio  extends PagingAndSortingRepository<Token, Integer>, CrudRepository<Token, Integer> {

    Token findTokenByTipoToken(String tipoToken);
}
