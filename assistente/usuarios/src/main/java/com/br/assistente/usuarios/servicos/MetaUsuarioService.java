package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.MetaUsuarios;

import java.util.Date;
import java.util.List;

public interface MetaUsuarioService {

    List<MetaUsuarios> listarTodasMetas() throws Exception ;
    MetaUsuarios salverMeta(MetaUsuarios metaUsuarios) throws Exception;
    MetaUsuarios listarMetaPorId(Integer id) throws Exception;
    List<MetaUsuarios> listarMetaPorUsuarioIdEData(Integer usuarioId, Date data) throws Exception ;
    MetaUsuarios pesquisarMetaPorId(Integer metaId) throws Exception ;
    MetaUsuarios atualizarMeta(MetaUsuarios metaUsuarios) throws Exception ;
    void deletarMeta(Integer id) throws Exception ;


}
