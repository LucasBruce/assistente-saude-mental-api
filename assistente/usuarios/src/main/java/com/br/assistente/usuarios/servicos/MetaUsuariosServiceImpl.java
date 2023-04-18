package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import com.br.assistente.usuarios.repositorios.MetaUsuariosRepositorio;
import com.br.assistente.usuarios.repositorios.MetasRepositorio;
import com.br.assistente.usuarios.repositorios.UsuariosRepositorio;
import com.br.assistente.usuarios.servicos.exception.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetaUsuariosServiceImpl implements MetaUsuarioService {

    private MetaUsuariosRepositorio metaUsuariosRepositorio;
    private MetasRepositorio metasRepositorio;
    private UsuariosRepositorio usuariosRepositorio;

    public MetaUsuariosServiceImpl(MetaUsuariosRepositorio metaUsuariosRepositorio, MetasRepositorio metasRepositorio, UsuariosRepositorio usuariosRepositorio) {
        this.metaUsuariosRepositorio = metaUsuariosRepositorio;
        this.metasRepositorio = metasRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
    }

    @Override
    public List<MetaUsuarios> listarTodasMetas() throws Exception {
        List<MetaUsuarios> metaUsuarios = new LinkedList<>();
        Iterator<MetaUsuarios> todasMetas = this.metaUsuariosRepositorio.findAll().iterator();
        while(todasMetas.hasNext()){
            metaUsuarios.add(todasMetas.next());
        }
        if (metaUsuarios.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return metaUsuarios;
    }

    @Override
    public MetaUsuarios salverMeta(MetaUsuarios metaUsuarios) throws Exception {
        this.validarSalvarVinculoMetaUsuario(metaUsuarios);
        return this.metaUsuariosRepositorio.save(metaUsuarios);
    }

    private void validarSalvarVinculoMetaUsuario(MetaUsuarios metaUsuarios) throws Exception {
        if (metaUsuarios.getMetaId() == null || this.metasRepositorio.findById(metaUsuarios.getMetaId()).isEmpty()) {
            throw new MetaEncontradaException();
        }
        if (metaUsuarios.getUsuarioId() == null || this.usuariosRepositorio.findById(metaUsuarios.getUsuarioId()).isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        if ( metaUsuarios.getUsuarioId() != null
                && metaUsuarios.getDataMeta() != null
                && metaUsuarios.getMetaId() != null
                && !this.metaUsuariosRepositorio.findByUsuarioIdAndDataMetaAndMetaId(metaUsuarios.getUsuarioId(), metaUsuarios.getDataMeta(), metaUsuarios.getMetaId()).isEmpty()) {
            throw new MetaJaCadastradaParaODiaException();
        }
    }

    @Override
    public MetaUsuarios listarMetaPorId(Integer id) throws Exception {
        Optional<MetaUsuarios> metaUsuarios = this.metaUsuariosRepositorio.findById(id);
        if (metaUsuarios.isEmpty()) {
            throw new MetasNaoExisteException();
        }
        return metaUsuarios.get();
    }

    @Override
    public List<MetaUsuarios> listarMetaPorUsuarioIdEData(Integer usuarioId, Date data) throws Exception{
        List<MetaUsuarios> metaUsuarios = new LinkedList<>();
        Iterator<MetaUsuarios> todasMetas = this.metaUsuariosRepositorio.findByUsuarioIdAndDataMeta(usuarioId, data).iterator();
        while(todasMetas.hasNext()){
            metaUsuarios.add(todasMetas.next());
        }
        if (metaUsuarios.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return metaUsuarios;
    }

    @Override
    public MetaUsuarios pesquisarMetaPorId(Integer metaId) throws Exception  {
        Optional<MetaUsuarios> metas = this.metaUsuariosRepositorio.findById(metaId);
        if(metas.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return metas.get();
    }

    @Override
    public MetaUsuarios atualizarMeta(MetaUsuarios metaUsuarios) throws Exception {
        try {
            if (metaUsuarios.getMetaId() == null || this.metasRepositorio.findById(metaUsuarios.getMetaId()).isEmpty()) {
                throw new MetaEncontradaException();
            }
            if (metaUsuarios.getUsuarioId() == null || this.usuariosRepositorio.findById(metaUsuarios.getUsuarioId()).isEmpty()) {
                throw new UsuarioNaoEncontradoException();
            }
            if (metaUsuarios.getId() == null || this.metaUsuariosRepositorio.findById(metaUsuarios.getId()).isEmpty()) {
                throw new MetasNaoExisteException();
            }
            return this.metaUsuariosRepositorio.save(metaUsuarios);
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deletarMeta(Integer id) throws Exception {
        try {
            this.pesquisarMetaPorId(id);
            this.metaUsuariosRepositorio.deleteById(id);
        }catch (Exception e) {
            throw e;
        }
    }





}
