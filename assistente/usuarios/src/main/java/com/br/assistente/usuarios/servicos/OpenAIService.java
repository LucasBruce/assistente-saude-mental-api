package com.br.assistente.usuarios.servicos;

import com.theokanning.openai.completion.CompletionRequest;

public interface OpenAIService {

    CompletionRequest requisitarMensagemOpenAI(String mensagem);

}
