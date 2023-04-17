package com.br.assistente.usuarios.repositorios;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MetaUsuariosRepositorio extends PagingAndSortingRepository<MetaUsuarios, Integer>, CrudRepository<MetaUsuarios, Integer> {

    List<MetaUsuarios> findByUsuarioIdAndMetaId(Integer usuarioId, Integer metaId);
    List<MetaUsuarios> findByUsuarioIdAndDataMeta(Integer usuarioId, Date dataMeta);
    List<MetaUsuarios> findByUsuarioIdAndDataMetaAndMetaId(Integer usuarioId, Date dataMeta, Integer metaId);
}
