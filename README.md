# assistente-saude-mental-api

#CRIACAO DE TAREFAS:

nome da branch: tarefa-bananinha-00;

#FINALIZACAO DA TAREFA:

merge tarefa-bananinha-00 na main; 

obs: se houver conflitos resolve o conflito na tarefa-bananinha-00 e depois da o merge na main.

#BACKEND

obsGeral: O backend terá a responsabilidade de descriptografar as senhas que vêm do 
frontend e salvar as informações do usuário no banco e fazer a autenticação do usuário.
A tradução dos textos serão feitas no frontend.

#1. Endpoint 

POST: 
{
  nome
  username
  email
  senha
}

obs: O back receberá essas informções e guardara no banco postegres.  

#2. Endpoint 

POST: 
{
  username/email
  senha
}

obs: O back receberá essas informações e fará a decriptação das senhas 
e verificar com o que existe no banco para saber se realmente é aquele
usuário.

#3. Endpoint

GET:
{
  nome
  username
  isUsername: boolean
}

obs: Como já havia dito a autenticação será feita no back então terá 
como retorno para a tela de login um boolean resultado do processamento do #2. Endpoint
e outras informações que serão usadas pela tela chat.

Stacks:

- frontend: node angular;

- backend: Java spring;

- banco: postgres;

- api chat-gpt

- infraestrutura: heroku;

- versionamento: git com github ou gitlab.

obs: Como a gente não terá muito tempo então achei melhor focarmos na aplicação e não na infra.
O heroku vai abstrair toda essa parte pra gente. Não é uma boa prática, mas como a gente não vai
ter muito tempo então sem testes unitários também. 

heroku suport:

- OpenJDK 17 LTS
- node version 18.15.0 LTS
- postgres 13

plano básico:

9 dolares; 

reais 47,52 é uma estimativa porque o dolar hj está a 5,25;

9,50 para cada.
