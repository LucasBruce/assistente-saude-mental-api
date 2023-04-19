package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Token;
import com.br.assistente.usuarios.utils.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OpenAIServiceImpl implements OpenAIService{

    private TokenService tokenService;

    public OpenAIServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public String requisitarMensagemOpenAI(String tema) throws Exception {

        Token token;
        StringBuilder sbTextoRetorno = new StringBuilder();
        StringBuilder mensagemSolicitacaoArtigo = new StringBuilder();
        StringBuilder mensagemDirecionnandoOProfissional = new StringBuilder();

        mensagemSolicitacaoArtigo.append(Constantes.SOLICITAR_ARTIGO_PARA_O_TEMA.replace(Constantes.REPLACE_PARA_TEMA, tema));
        mensagemDirecionnandoOProfissional.append(Constantes.SYSTEM_ROLE_PARA_ARTIGOS.replace((Constantes.REPLACE_PARA_TEMA), tema));

        try {
            token = this.tokenService.listarTokenPorTipoToken(Constantes.TIPO_TOKEN_OPENAI);
            OpenAiService service = new OpenAiService(token.getToken());

            final List<ChatMessage> messages = new ArrayList<>();
            final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), mensagemDirecionnandoOProfissional.toString());
            final ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), mensagemSolicitacaoArtigo.toString());
            messages.add(systemMessage);
            messages.add(userMessage);

               ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model(Constantes.MODEL_DEFAULT_OPENAI)
                    .messages(messages)
                    .n(1)
                    .logitBias(new HashMap<>())
                    .build();


            service.streamChatCompletion(chatCompletionRequest).blockingForEach(onNext -> {
                onNext.getChoices().forEach(texto ->{
                    if (texto.getMessage().getContent() != null) {
                        sbTextoRetorno.append(texto.getMessage().getContent());
                    }

                });
            });


            service.shutdownExecutor();

        }catch (Exception e) {
            throw e;
        }
        return sbTextoRetorno.toString();
    }
}
