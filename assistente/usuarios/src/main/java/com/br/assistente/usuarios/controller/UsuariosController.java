package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.Usuarios;
import com.br.assistente.usuarios.servicos.exception.UsuarioNaoEncontradoException;
import com.br.assistente.usuarios.servicos.UsuarioService;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constantes.PATH_USUARIOS)
@Tag(name="API para gerenciar usuarios", description = "API para criar, deletar, alterar e inserir usuários no sistema")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Criação de usuários no sistema")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    public ResponseEntity<Usuarios> criarUsuario(@RequestBody Usuarios usuarios) {
        try {
            usuarios = this.usuarioService.salvarUsuario(usuarios);

        } catch (Exception e) {
            return new ResponseEntity("erro salvando usuário: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Usuarios>(usuarios, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Recuperar vários usuários")
    @ApiResponse(responseCode = "200", description = "Usuários recuperados com sucesso")
    public ResponseEntity findAllUsers() {
        try {
            List<Usuarios> todosUsuarios = this.usuarioService.getTodosUsuarios();
            if (todosUsuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return new ResponseEntity<List<Usuarios>>(todosUsuarios, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping(value = "{id}")
    @Operation(summary = "Recupera usuário pelo seu identificador único")
    @ApiResponse(responseCode = "200", description = "Usuário recuperado com sucesso")
    public ResponseEntity findUser(@PathVariable("id") Integer id ) {
        try {
            Usuarios usuario = this.usuarioService.getUsuario(id);
            if(usuario == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Operation(summary = "Atualiza por completo o usuário")
    @ApiResponse(responseCode = "200", description = "Usuário foi atualizado com sucesso")
    public ResponseEntity atualizarPorCompletoUsuario(@RequestBody Usuarios usuario) {

        try{
            Usuarios usuarioAtualizado = this.usuarioService.atualizarUsuario(usuario);
            return new ResponseEntity<Usuarios>(usuarioAtualizado, HttpStatus.OK);
        }catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete um usuário do sistema")
    @ApiResponse(responseCode = "200", description = "Usuário deletado do sistema")
    public ResponseEntity deletarUsuario(@PathVariable("id") Integer id) {
        try {
            this.usuarioService.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


