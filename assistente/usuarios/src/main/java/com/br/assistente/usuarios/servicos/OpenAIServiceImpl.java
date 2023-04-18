package com.br.assistente.usuarios.servicos;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class OpenAIServiceImpl implements OpenAIService{


    @Override
    public CompletionRequest requisitarMensagemOpenAI(String mensagem) {
        OpenAiService openAiService = new OpenAiService("");
        return null;
    }
}
