package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Usuarios;

import java.util.List;

public interface UsuarioService {

    List<Usuarios> getTodosUsuarios() throws Exception;
    Usuarios getUsuario(Integer id) throws Exception;
    Usuarios salvarUsuario(Usuarios usuarios) throws Exception;
    Usuarios atualizarUsuario(Usuarios usuario) throws Exception;
    void deletarUsuario(Integer id) throws  Exception;
}
