package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.Consultas;
import com.br.assistente.usuarios.servicos.ConsultaService;
import com.br.assistente.usuarios.servicos.exception.HoraDaConsultaNaoPresenteException;
import com.br.assistente.usuarios.servicos.exception.ProfissionalNaoExisteException;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.UsuarioNaoEncontradoException;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.PATH_CONSULTAS)
@CrossOrigin(origins = {"http://localhost:4200"})
public class ConsultasController {

    private ConsultaService consultaService;

    public ConsultasController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    @Operation(description = "Retorna todas as consultas")
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso das consultas")
    public ResponseEntity listarConsultas() {
        try {
            return new ResponseEntity(this.consultaService.listaTodasConsultas(), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/porUsuarios/{id}")
    @Operation(description = "lista por usuário id")
    @ApiResponse(responseCode = "200", description = "Retorno de sucesso das consultas ")
    public ResponseEntity listaConsultasPorUsuarioId(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity(this.consultaService.listaConsultasPorUsuarioId(id), HttpStatus.OK);
        }catch (UsuarioNaoEncontradoException e) {
            return new ResponseEntity("Usuário informado não existe",HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/porProfissionais/{id}")
    @Operation(description = "lista profissoinais por ID")
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso das consultas")
    public ResponseEntity listaConsultasPorProfissionaisId(@PathVariable("id") Integer id ) {
        try {
            return new ResponseEntity(this.consultaService.listarConsultasPorProfissionalId(id), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(description = "adiciona uma consulta")
    @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso")
    public ResponseEntity adicionarConsulta(@RequestBody Consultas consultas) {
        try{
            return new ResponseEntity(this.consultaService.adicionarConsultas(consultas), HttpStatus.OK);
        }catch (UsuarioNaoEncontradoException e){
            return new ResponseEntity("Usuário não encontrado ou não informado!", HttpStatus.BAD_REQUEST);
        } catch (ProfissionalNaoExisteException e) {
            return new ResponseEntity("Profissional não encontrado ou não informado", HttpStatus.BAD_REQUEST);
        } catch (HoraDaConsultaNaoPresenteException e) {
            return new ResponseEntity("Hora da consulta é obrigatória", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Operation(description = "atualiza uma consulta")
    @ApiResponse(responseCode = "200", description = "registro atualizado com sucesso")
    public ResponseEntity atualizarConsulta(@RequestBody Consultas consultas) {
        try{
            return new ResponseEntity(this.consultaService.atualizarConsultas(consultas), HttpStatus.OK);
        }catch (UsuarioNaoEncontradoException e){
            return new ResponseEntity("Usuário não encontrado ou não informado!", HttpStatus.BAD_REQUEST);
        } catch (ProfissionalNaoExisteException e) {
            return new ResponseEntity("Profissional não encontrado ou não informado", HttpStatus.BAD_REQUEST);
        } catch (HoraDaConsultaNaoPresenteException e) {
            return new ResponseEntity("Hora da consulta é obrigatória", HttpStatus.BAD_REQUEST);
        }catch (RegistroNaoExistenteException e) {
            return new ResponseEntity("Consulta ID não informado ou inexistente", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "deleta uma consulta")
    @ApiResponse(responseCode = "200", description = "registro deletado com sucesso")
    public ResponseEntity deletarConsulta(@PathVariable("id") Integer id) {
        try{
            this.consultaService.deletarConsultaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
