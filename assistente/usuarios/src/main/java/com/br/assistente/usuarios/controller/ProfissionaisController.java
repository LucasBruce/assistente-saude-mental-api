package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.Profissionais;
import com.br.assistente.usuarios.servicos.ProfissionaisService;
import com.br.assistente.usuarios.servicos.exception.EspecialidadeNaoEncontradaException;
import com.br.assistente.usuarios.servicos.exception.NomeProfissionalVazioException;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.RegistroProfissionalException;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import retrofit2.http.Path;

@RestController
@RequestMapping(Constantes.PATH_PROFISSIONAIS)
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProfissionaisController {

    private ProfissionaisService profissionaisService;

    public ProfissionaisController(ProfissionaisService profissionaisService) {
        this.profissionaisService = profissionaisService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna os profissionais cadastrados no sistema")
    @ApiResponse(responseCode = "200", description = "Profissional retornado com sucesso")
    public ResponseEntity listarProfissionalPorId(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity(this.profissionaisService.listarProfissionalPorId(id), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(description = "Cadastra o profissional na plataforma")
    @ApiResponse(responseCode = "201", description = "Profissional criado com sucesso")
    public ResponseEntity cadastrarProfissional(@RequestBody Profissionais profissionais ){
        try {
            return new ResponseEntity(this.profissionaisService.adicionarProfissionais(profissionais), HttpStatus.CREATED);
        }catch (EspecialidadeNaoEncontradaException e) {
            return new ResponseEntity("A especilidade não foi informada", HttpStatus.BAD_REQUEST);
        }catch (RegistroProfissionalException e) {
            return new ResponseEntity("Registro da profissão é obrigatório", HttpStatus.BAD_REQUEST);
        }catch (NomeProfissionalVazioException e) {
            return new ResponseEntity("O nome do profissional é obrigatório", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    @Operation(summary = "Retorna todos os profissionais", description =  "Retorna todos os profissionais")
    @ApiResponse(responseCode = "200", description = "Profissionais retornados com sucesso")
    private ResponseEntity listarProfissionais(){
        try{
            return new ResponseEntity(this.profissionaisService.listarTodos(), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Erro ao consultar registros", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/especialidade/{valor}")
    @Operation(summary = "Retorna registros de profissionais por especialidade", description = "Retorna registros de profissionais por especialidade")
    @ApiResponse(responseCode = "200", description = "Profissionais retornados com sucesso")
    private ResponseEntity listaProfissionaisPorEspecialidades(@PathVariable("valor") String especialidade) {
        try {
            return new ResponseEntity(this.profissionaisService.listarProfissionaisPorEspecialidade(especialidade), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Operation(summary = "Atualizar um registro do profissional", description = "Atualizar um registro")
    @ApiResponse(responseCode = "200", description = "registro atualizado com sucesso")
    public ResponseEntity atualizarRegistr(@RequestBody Profissionais profissionais) {
        try {
            return new ResponseEntity(this.profissionaisService.atualizarProfissionais(profissionais),HttpStatus.OK);
        }catch (EspecialidadeNaoEncontradaException e) {
            return new ResponseEntity("A especilidade não foi informada", HttpStatus.BAD_REQUEST);
        }catch (RegistroProfissionalException e) {
            return new ResponseEntity("Registro da profissão é obrigatório", HttpStatus.BAD_REQUEST);
        }catch (NomeProfissionalVazioException e) {
            return new ResponseEntity("O nome do profissional é obrigatório", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um profissional da plataforma", description = "Deleta um profissional da plataforma")
    @ApiResponse(responseCode = "200", description = "profissional deletado com sucesso")
    public ResponseEntity deletarProfissional(@PathVariable("id") Integer id) {
        try {
            this.profissionaisService.deletarProfissionais(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}


