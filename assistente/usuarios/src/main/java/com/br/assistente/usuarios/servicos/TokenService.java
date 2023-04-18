package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Token;

import java.util.List;

public interface TokenService {

    List<Token> listarTodosOsTokens() throws Exception ;
    Token listarTokenPorId(Integer id) throws Exception;
    Token listarTokenPorTipoToken(String tipoToken) throws Exception ;
    Token cadastrarToken(Token token) throws Exception ;
    Token atualizarToken(Token token) throws Exception ;
    void deletarToken(Integer id) throws Exception ;

}
