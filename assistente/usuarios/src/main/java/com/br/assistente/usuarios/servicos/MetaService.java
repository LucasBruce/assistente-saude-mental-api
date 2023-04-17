package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Metas;

import java.util.List;

public interface MetaService {

    List<Metas> listarMetas() throws Exception;
    Metas salvarMeta(Metas metas) throws Exception;
    Metas atualizarMeta(Metas metas) throws Exception;
    void deletarMeta(Integer idMeta) throws Exception;
    Metas findMetaById(Integer idMeta) throws Exception;

}
