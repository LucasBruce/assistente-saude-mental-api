package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Profissionais;
import com.br.assistente.usuarios.repositorios.ProfissionaisRepositorio;
import com.br.assistente.usuarios.servicos.exception.EspecialidadeNaoEncontradaException;
import com.br.assistente.usuarios.servicos.exception.NomeProfissionalVazioException;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.RegistroProfissionalException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ProfissionaisServiceImpl implements ProfissionaisService{

    private ProfissionaisRepositorio profissionaisRepositorio;

    public ProfissionaisServiceImpl(ProfissionaisRepositorio profissionaisRepositorio) {
        this.profissionaisRepositorio = profissionaisRepositorio;
    }

    @Override
    public List<Profissionais> listarTodos() throws Exception {
        Iterator<Profissionais> profissionais = this.profissionaisRepositorio.findAll().iterator();
        List<Profissionais> profissionaisList = new LinkedList<>();
        while(profissionais.hasNext()){
            profissionaisList.add(profissionais.next());
        }

        if (profissionaisList.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }

        return profissionaisList;
    }

    @Override
    public Profissionais adicionarProfissionais(Profissionais profissionais) throws Exception {
        this.validarInformacoesProfissionais(profissionais);
        return this.profissionaisRepositorio.save(profissionais);
    }

    @Override
    public Profissionais listarProfissionalPorId(Integer id) throws Exception {
        Optional<Profissionais> profissionais = this.profissionaisRepositorio.findById(id);
        if(profissionais.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return profissionais.get();
    }

    @Override
    public List<Profissionais> listarProfissionaisPorEspecialidade(String especialidade) throws Exception {
        List<Profissionais> profissionais = this.profissionaisRepositorio.findByEspecialidade(especialidade);
        if(profissionais.isEmpty()){
            throw new RegistroNaoExistenteException();
        }
        return profissionais;



    }

    @Override
    public Profissionais atualizarProfissionais(Profissionais profissionais) throws Exception {
        if (profissionais.getId() == null || this.listarProfissionalPorId(profissionais.getId()) == null ){
            throw new RegistroNaoExistenteException();
        }
       this.validarInformacoesProfissionais(profissionais);

        return this.profissionaisRepositorio.save(profissionais);
    }

    @Override
    public void deletarProfissionais(Integer id) throws Exception{

        if(this.listarProfissionalPorId(id) == null) {
            throw new RegistroNaoExistenteException();
        }

        this.profissionaisRepositorio.deleteById(id);
    }

    private void validarInformacoesProfissionais(Profissionais profissionais) throws Exception {
        if (profissionais.getEspecialidade() == null || profissionais.getEspecialidade().trim().isEmpty()) {
            throw new EspecialidadeNaoEncontradaException();
        }
        if (profissionais.getRegistroProfissional() == null || profissionais.getRegistroProfissional().trim().isEmpty()) {
            throw new RegistroProfissionalException();
        }

        if (profissionais.getNome() == null || profissionais.getNome().isEmpty()) {
            throw new NomeProfissionalVazioException();
        }
    }
}
