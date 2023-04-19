package com.br.assistente.usuarios.controller;

import com.br.assistente.usuarios.servicos.OpenAIService;
import com.br.assistente.usuarios.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.ARTIGOS_PATH)
public class ArtigosController {

    private OpenAIService openAIService;

    public ArtigosController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/{tema}")
    @ApiResponse(responseCode = "200", description = "artigo retornado com sucesso")
    @Operation(summary = "Recupera um artigo de um tema específico", description = "Recupera um artigo de um tema específico")
    public ResponseEntity retornarArtigo(@PathVariable("tema") String tema) {
        try{
            return new ResponseEntity(this.openAIService.requisitarMensagemOpenAI(tema), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
