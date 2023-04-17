package com.br.assistente.usuarios.servicos;


import com.br.assistente.usuarios.entidades.Metas;
import com.br.assistente.usuarios.repositorios.MetasRepositorio;
import com.br.assistente.usuarios.servicos.exception.MetaEncontradaException;
import com.br.assistente.usuarios.servicos.exception.MetasNaoExisteException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service

public class MetaServiceImpl implements MetaService {

    private MetasRepositorio metasRepositorio;

    public MetaServiceImpl(MetasRepositorio metasRepositorio) {
        this.metasRepositorio = metasRepositorio;
    }

    @Override
    public List<Metas> listarMetas() throws Exception {
        List<Metas> metas = new LinkedList<>();
        Iterator<Metas> metasIterator = this.metasRepositorio.findAll().iterator();

        while (metasIterator.hasNext()) {
            metas.add(metasIterator.next());
        }
        if (metas.isEmpty()) {
            throw new MetasNaoExisteException();
        }
        return metas;
    }

    @Override
    public Metas salvarMeta(Metas metas) throws Exception {
        if (metas.getId() != null && !this.metasRepositorio.findById(metas.getId()).isEmpty()) {
            throw new MetaEncontradaException();
        }
        return this.metasRepositorio.save(metas);
    }

    @Override
    public Metas atualizarMeta(Metas metas) throws Exception {
        if ( metas.getId() != null && this.metasRepositorio.findById(metas.getId()).isEmpty()) {
            throw new MetasNaoExisteException();
        }
        return this.metasRepositorio.save(metas);
    }

    @Override
    public void deletarMeta(Integer idMeta) throws Exception{
        if(this.metasRepositorio.findById(idMeta).isEmpty()) {
            throw new MetasNaoExisteException();
        }
        this.metasRepositorio.deleteById(idMeta);
    }

    @Override
    public Metas findMetaById(Integer idMeta) throws Exception {
        Optional<Metas>  metas = this.metasRepositorio.findById(idMeta);
        if (metas.isEmpty()) {
            throw new MetasNaoExisteException();
        }
        return metas.get();
    }




}
