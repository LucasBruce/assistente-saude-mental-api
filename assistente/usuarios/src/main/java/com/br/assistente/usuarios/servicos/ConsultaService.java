package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Consultas;

import java.util.List;

public interface ConsultaService {

    List<Consultas> listaTodasConsultas() throws Exception ;
    List<Consultas> listaConsultasPorUsuarioId(Integer usuarioId) throws Exception ;
    List<Consultas> listarConsultasPorProfissionalId(Integer profissionalId) throws Exception ;
    Consultas adicionarConsultas(Consultas consultas) throws Exception ;
    Consultas atualizarConsultas(Consultas consultas) throws Exception ;
    void deletarConsultaPorId(Integer consultaId) throws Exception ;
}
