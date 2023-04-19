package com.br.assistente.usuarios.servicos;

import com.theokanning.openai.completion.CompletionRequest;

public interface OpenAIService {

    String requisitarMensagemOpenAI(String mensagem) throws Exception;

}
