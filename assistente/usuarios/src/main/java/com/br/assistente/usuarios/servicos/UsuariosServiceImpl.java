package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Usuarios;
import com.br.assistente.usuarios.repositorios.UsuariosRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class UsuariosServiceImpl  implements UsuarioService{

    private UsuariosRepositorio usuariosRepositorio;

    public UsuariosServiceImpl(UsuariosRepositorio usuariosRepositorio) {
        this.usuariosRepositorio = usuariosRepositorio;
    }

    @Override
    public List<Usuarios> getTodosUsuarios() throws Exception {
        List<Usuarios> todosUsuarios = new LinkedList<Usuarios>();
        Iterator<Usuarios> iterator =  this.usuariosRepositorio.findAll().iterator();
        while(iterator.hasNext()) {
            todosUsuarios.add(iterator.next());
        }
        return todosUsuarios;
    }

    @Override
    public Usuarios getUsuario(Integer id) throws Exception {

        if (id == 0) {
            throw new RuntimeException("ID requred");
        }

        Optional<Usuarios> usuarios = this.usuariosRepositorio.findById(id);
        if (!usuarios.isEmpty()) {
            return usuarios.get();
        }
    return null;

    }

    @Override
    public Usuarios salvarUsuario(Usuarios usuarios) throws Exception {
        return this.usuariosRepositorio.save(usuarios);
    }

    @Override
    public Usuarios atualizarUsuario(Usuarios usuario) throws Exception {

        if (this.usuariosRepositorio.findById(usuario.getId()) == null) {
            throw new UsuarioNaoEncontradoException();
        }
        return this.usuariosRepositorio.save(usuario);
    }
}
