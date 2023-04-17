package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import com.br.assistente.usuarios.servicos.MetaUsuarioService;
import com.br.assistente.usuarios.servicos.exception.MetaEncontradaException;
import com.br.assistente.usuarios.servicos.exception.MetaJaCadastradaParaODiaException;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExitenteException;
import com.br.assistente.usuarios.servicos.exception.UsuarioNaoEncontradoException;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.PATH_META_USUARIOS)
@Tag(name = "path para gerenciamento da relação das metas com usuários", description ="path para gerenciamento da relação das metas com usuários")
public class MetaUsuariosController {

    private MetaUsuarioService metaUsuarioService;

    public MetaUsuariosController(MetaUsuarioService metaUsuarioService) {
        this.metaUsuarioService = metaUsuarioService;
    }

    @GetMapping
    @Operation(summary = "pesquisa todos os vínculos")
    @ApiResponse(responseCode = "200", description = "Vínculos retornados com sucesso")
    public ResponseEntity retornarMetaUsuarios(){
        try{
            return new ResponseEntity(this.metaUsuarioService.listarTodasMetas(), HttpStatus.OK);
        } catch(RegistroNaoExitenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            return new ResponseEntity("Erro ao pesquisar vínculos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Vincula um usuário a uma meta e uma data")
    @ApiResponse(responseCode = "201", description = "Vinculos feitos com sucesso")
    public ResponseEntity vincularMetaAoUsuario(@RequestBody MetaUsuarios metaUsuarios) {
        try {
            return new ResponseEntity(this.metaUsuarioService.salverMeta(metaUsuarios), HttpStatus.OK);
        } catch (MetaEncontradaException e) {
            return new ResponseEntity("Meta informada não foi encontrada", HttpStatus.NOT_FOUND);
        } catch (UsuarioNaoEncontradoException e) {
            return new ResponseEntity("Usuário informado não foi encontrado", HttpStatus.NOT_FOUND);
        } catch (MetaJaCadastradaParaODiaException e) {
            return new ResponseEntity("Meta já cadastrada para o usuário e o dia", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Erro ao tentar salvar a meta para o dia e usuário", HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }
}
