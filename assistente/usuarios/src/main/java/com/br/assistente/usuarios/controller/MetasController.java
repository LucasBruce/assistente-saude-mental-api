package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.Metas;
import com.br.assistente.usuarios.servicos.MetaService;
import com.br.assistente.usuarios.servicos.exception.MetaEncontradaException;
import com.br.assistente.usuarios.servicos.exception.MetasNaoExisteException;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.PATH_METAS)
public class MetasController {

    private MetaService metaService;

    public MetasController(MetaService metaService) {
        this.metaService = metaService;
    }

    @PostMapping
    @Operation(summary = "Inclui uma nova meta")
    @ApiResponse(responseCode = "201", description = "meta criada com sucesso")
    public ResponseEntity criarMeta(@RequestBody Metas metas ){
        try {
            Metas metaCadastrada = this.metaService.salvarMeta(metas);
            return new ResponseEntity(metaCadastrada, HttpStatus.CREATED);
        } catch (MetaEncontradaException e) {
            return new ResponseEntity<String>("Meta já existe", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Operation(summary = "Pega todas as metas cadatradas")
    @ApiResponse(responseCode = "200", description = "metas retornadas com sucesso")
    public ResponseEntity getMetas() {
        try {
            return new ResponseEntity(this.metaService.listarMetas(), HttpStatus.OK);
        }catch (MetasNaoExisteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao encontrar metas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pega uma meta específica")
    @ApiResponse(responseCode = "200", description = "Meta retornada com sucesso")
    public ResponseEntity getMeta(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(this.metaService.findMetaById(id), HttpStatus.OK);
        }catch (MetaEncontradaException  | MetasNaoExisteException e ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Erro ao encontrar meta específica", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma meta específica")
    @ApiResponse(responseCode = "200", description = "Meta deletada com sucesso")
    public ResponseEntity deletarMeta(@PathVariable(value = "id") Integer id) {
        try{
            this.metaService.deletarMeta(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            return new ResponseEntity<String>("Meta não deletada. Verifique se há algum vínculo com metas diárias e tente novamente", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Operation(summary = "Atualiza por completo uma meta")
    @ApiResponse(responseCode = "200", description = "Meta atualizada com sucesso")
    public ResponseEntity atualizarMeta(@RequestBody Metas meta) {
        try {
            return new ResponseEntity(this.metaService.atualizarMeta(meta), HttpStatus.OK);
        }catch (MetasNaoExisteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}

