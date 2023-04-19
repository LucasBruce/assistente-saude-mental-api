package com.br.assistente.usuarios.utils;

public class Constantes {

    private static final String API_VERSION = "/api/v1";
    public static final String PATH_USUARIOS = "/usuarios";
    public static final String PATH_METAS = "/metas";
    public static final String PATH_META_USUARIOS = "/usuarios/metas";
    public static final String PATH_TOKEN = "/token";
    public static final String TIPO_TOKEN_OPENAI = "TOKEN_OPENAI";
    public static final String MODEL_DEFAULT_OPENAI = "gpt-3.5-turbo";
    public static final String ARTIGOS_PATH = "/artigo";
    public static final String SOLICITAR_ARTIGO_PARA_O_TEMA = "Crie um artigo para o tema: #tema";
    public static final String SYSTEM_ROLE_PARA_ARTIGOS = "Você é um doutor especialista no tema: #tema";
    public static final String REPLACE_PARA_TEMA = "#tema";
    public static final String PATH_PROFISSIONAIS = "/profissionais";
}
