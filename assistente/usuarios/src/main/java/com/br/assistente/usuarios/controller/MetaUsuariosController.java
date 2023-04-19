package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.entidades.MetaUsuarios;
import com.br.assistente.usuarios.servicos.MetaUsuarioService;
import com.br.assistente.usuarios.servicos.exception.*;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(Constantes.PATH_META_USUARIOS)
@CrossOrigin(origins = {"http://localhost:4200"})
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
        } catch(RegistroNaoExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e) {
            return new ResponseEntity("Erro ao pesquisar vínculos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "pesquisar meta por ID")
    @ApiResponse(responseCode = "200", description = "Vínculo retornado com sucesso")
    public ResponseEntity retornarMetaPorId(@PathVariable("id") Integer id) {
        try {
         return new ResponseEntity(this.metaUsuarioService.listarMetaPorId(id), HttpStatus.OK);
        }catch(MetasNaoExisteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return new ResponseEntity("Erro ao pesquisar meta ", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/usuario/{id}/data/{data}")
    @Operation(summary = "pesquisa as metas diárias de um usuário")
    @ApiResponse(responseCode = "200", description = "Metas diárias retornadas com sucesso")
    public ResponseEntity retornarMetaPorUsuario(@PathVariable("id") Integer id, @PathVariable("data") String data) {
        try {
            Date dataConvertida = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            return new ResponseEntity(this.metaUsuarioService.listarMetaPorUsuarioIdEData(id, dataConvertida), HttpStatus.OK);
        }catch (RegistroNaoExistenteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (ParseException e) {
            return new ResponseEntity("Data informada é inválida. Informe no formato: yyyy/MM/dd (Ano - 4 dígitos, mês - 2 dígitos, dia - 2 dígitos)", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity("Erro ao consultar metas para o usuário e dia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    @Operation(summary = "Vincula um usuário a uma meta e uma data")
    @ApiResponse(responseCode = "201", description = "Vinculos feitos com sucesso")
    public ResponseEntity vincularMetaAoUsuario(@RequestBody MetaUsuarios metaUsuarios) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(metaUsuarios.getDataMeta());
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            metaUsuarios.setDataMeta(cal.getTime());

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

    @PutMapping
    @Operation(summary = "Atualizar uma meta para um usuário específico")
    @ApiResponse(responseCode = "200", description = "meta atualizada com sucesso")
    public ResponseEntity atualizarMeta(@RequestBody MetaUsuarios metaUsuarios) {
        try {
            return new ResponseEntity(this.metaUsuarioService.atualizarMeta(metaUsuarios), HttpStatus.OK);
        }catch (MetaEncontradaException e) {
            return new ResponseEntity("Meta informada não foi encontrada", HttpStatus.BAD_REQUEST);
        } catch (UsuarioNaoEncontradoException e) {
            return new ResponseEntity("Usuário informado não foi encontrado", HttpStatus.BAD_REQUEST);
        } catch(MetasNaoExisteException e) {
            return new ResponseEntity("Meta informada (ID) não existente", HttpStatus.BAD_REQUEST);
        } catch (MetaJaCadastradaParaODiaException e) {
            return new ResponseEntity("Meta já cadastrada para o usuário e dia", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Erro ao atualizar meta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um meta")
    @ApiResponse(responseCode = "200", description = "Meta deletada com sucesso")
    public ResponseEntity deletarMeta(@PathVariable("id") Integer id) {
        try{
            this.metaUsuarioService.deletarMeta(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RegistroNaoExistenteException e) {
            return new ResponseEntity("Meta informada (ID) não existe", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
