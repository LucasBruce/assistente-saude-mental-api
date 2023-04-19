package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Profissionais;

import java.util.List;

public interface ProfissionaisService {

    List<Profissionais> listarTodos() throws Exception;
    Profissionais adicionarProfissionais(Profissionais profissionais) throws Exception;
    Profissionais listarProfissionalPorId(Integer id) throws Exception;
    List<Profissionais> listarProfissionaisPorEspecialidade(String especialidade) throws Exception;
    Profissionais atualizarProfissionais(Profissionais profissionais) throws Exception;
    void deletarProfissionais(Integer id) throws Exception;
}
