package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Consultas;
import com.br.assistente.usuarios.repositorios.ConsultaRepositorio;
import com.br.assistente.usuarios.repositorios.ProfissionaisRepositorio;
import com.br.assistente.usuarios.repositorios.UsuariosRepositorio;
import com.br.assistente.usuarios.servicos.exception.HoraDaConsultaNaoPresenteException;
import com.br.assistente.usuarios.servicos.exception.ProfissionalNaoExisteException;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.UsuarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService{

    private ConsultaRepositorio consultaRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private ProfissionaisRepositorio profissionaisRepositorio;

    public ConsultaServiceImpl(ConsultaRepositorio consultaRepositorio, UsuariosRepositorio usuariosRepositorio, ProfissionaisRepositorio profissionaisRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.profissionaisRepositorio = profissionaisRepositorio;
    }


    @Override
    public List<Consultas> listaTodasConsultas() throws Exception {
        Iterator<Consultas> consultasIterator = this.consultaRepositorio.findAll().iterator();
        List<Consultas> consultas = new LinkedList<>();
        while(consultasIterator.hasNext()) {
            consultas.add(consultasIterator.next());
        }
        if (consultas.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return consultas;
    }

    @Override
    public List<Consultas> listaConsultasPorUsuarioId(Integer usuarioId) throws Exception {

        if (usuarioId == null || usuarioId == 0 ) {
            throw new UsuarioNaoEncontradoException();
        }

        if (this.usuariosRepositorio.findById(usuarioId).isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        List<Consultas> consultas = this.consultaRepositorio.findByUsuarioId(usuarioId);
        if(consultas.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }

        return consultas;



    }

    @Override
    public List<Consultas> listarConsultasPorProfissionalId(Integer profissionalId) throws Exception  {

        List<Consultas> consultas = this.consultaRepositorio.findByProfissionalId(profissionalId);
        if(consultas.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }

        return consultas;
    }

    @Override
    public Consultas adicionarConsultas(Consultas consultas)  throws  Exception {

        this.validarConsulta(consultas);
        return this.consultaRepositorio.save(consultas);
    }

    @Override
    public Consultas atualizarConsultas(Consultas consultas) throws  Exception {

        this.validarConsulta(consultas);
        if (consultas.getId() == null || consultas.getId() == 0 || this.consultaRepositorio.findById(consultas.getId()).isEmpty()) {
            throw new RegistroNaoExistenteException();
        }

        return this.consultaRepositorio.save(consultas);
    }

    @Override
    public void deletarConsultaPorId(Integer consultaId) throws Exception {
        this.consultaRepositorio.deleteById(consultaId);
    }

    private void validarConsulta(Consultas consultas) throws Exception {

        if (consultas.getUsuarioId() == null || this.usuariosRepositorio.findById(consultas.getUsuarioId()).isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        if (consultas.getProfissionalId() == null || this.profissionaisRepositorio.findById(consultas.getProfissionalId()).isEmpty()) {
            throw new ProfissionalNaoExisteException();
        }

        if (consultas.getDataHoraConsulta() == null) {
            throw new HoraDaConsultaNaoPresenteException();
        }
    }


}
