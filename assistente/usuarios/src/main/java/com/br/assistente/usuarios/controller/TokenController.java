package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.Token;
import com.br.assistente.usuarios.servicos.TokenService;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.TipoTokenInexistenteException;
import com.br.assistente.usuarios.servicos.exception.TipoTokenJaCadastrado;
import com.br.assistente.usuarios.servicos.exception.TokenNaoVeioNaRequisicaoException;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.PATH_TOKEN)
@CrossOrigin(origins = {"http://localhost:4200"})
@Tag(name = "path para gerenciamento do token utilizado para conexão com o OPENAPI", description ="path para gerenciamento do token utilizado para conexão com o OPENAPI")

public class TokenController {

    private TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    @Operation(summary = "listar todos os tokens presentes na base", description = "listar todos os tokens presentes na base")
    @ApiResponse(responseCode = "200", description = "tokens retornados com sucesso")
    public ResponseEntity listarTodosOsTokens() {
        try {
            return new ResponseEntity(this.tokenService.listarTodosOsTokens(),HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return new ResponseEntity("Não foi possível retornar todos os tokens", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista token por id", description = "Lista token por id")
    @ApiResponse(responseCode = "200", description = "token retornado com sucesso")
    public ResponseEntity listarTokenPorId(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(this.tokenService.listarTokenPorId(id), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tipoToken/{tipoToken}")
    @Operation(summary = "Lista token por tipo de token", description = "Lista token por tipo de token")
    @ApiResponse(responseCode = "200", description = "token retornado com sucesso")
    public ResponseEntity listarPorTipoToken(@PathVariable("tipoToken") String tipoToken) {
        try{
            return new ResponseEntity(this.tokenService.listarTokenPorTipoToken(tipoToken),HttpStatus.OK);
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(summary = "Cadastra um token", description = "Cadastra um token")
    @ApiResponse(responseCode = "201", description = "token cadastrado com sucesso")
    public ResponseEntity cadastrarToken(@RequestBody Token token) {
        try {
            return new ResponseEntity(this.tokenService.cadastrarToken(token), HttpStatus.CREATED);
        }catch (TipoTokenInexistenteException e){
            return new ResponseEntity("Tipo token deve ser: ".concat(Constantes.TIPO_TOKEN_OPENAI), HttpStatus.BAD_REQUEST);
        }catch (TokenNaoVeioNaRequisicaoException e) {
            return new ResponseEntity("O valor do token é obrigatório!", HttpStatus.BAD_REQUEST);
        }catch (TipoTokenJaCadastrado e) {
            return new ResponseEntity("O tipo token:".concat(Constantes.TIPO_TOKEN_OPENAI).concat(" já está cadastrado!"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping
    @Operation(summary = "atualiza um token", description = "atualiza um token")
    @ApiResponse(responseCode = "200", description = "token atualizado com sucesso")
    public ResponseEntity atualizarToken(@RequestBody Token token) {
        try {
            return new ResponseEntity(this.tokenService.atualizarToken(token), HttpStatus.OK);
        } catch (TipoTokenInexistenteException e){
            return new ResponseEntity("Tipo token deve ser: ".concat(Constantes.TIPO_TOKEN_OPENAI), HttpStatus.BAD_REQUEST);
        }catch (TokenNaoVeioNaRequisicaoException e) {
            return new ResponseEntity("O valor do token é obrigatóri!", HttpStatus.BAD_REQUEST);
        }catch (RegistroNaoExistenteException e) {
            return new ResponseEntity("O ID informado do token não foi encontrado ou está faltando", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleta um token", description = "deleta um token")
    @ApiResponse(responseCode = "200", description = "token deletado com sucesso")
    public ResponseEntity deletarToken(@PathVariable("id") Integer id) {
        try {
            this.tokenService.deletarToken(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
