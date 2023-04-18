package com.br.assistente.usuarios.servicos;

import com.br.assistente.usuarios.entidades.Token;
import com.br.assistente.usuarios.repositorios.TokenRepositorio;
import com.br.assistente.usuarios.servicos.exception.RegistroNaoExistenteException;
import com.br.assistente.usuarios.servicos.exception.TipoTokenInexistenteException;
import com.br.assistente.usuarios.servicos.exception.TipoTokenJaCadastrado;
import com.br.assistente.usuarios.servicos.exception.TokenNaoVeioNaRequisicaoException;
import com.br.assistente.usuarios.utils.Constantes;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService{

    private TokenRepositorio tokenRepositorio;

    public TokenServiceImpl(TokenRepositorio tokenRepositorio) {
        this.tokenRepositorio = tokenRepositorio;
    }

    @Override
    public List<Token> listarTodosOsTokens() throws Exception {

        Iterator<Token> tokenIterator = this.tokenRepositorio.findAll().iterator();
        List<Token> tokens = new LinkedList<>();

        while(tokenIterator.hasNext()){
            tokens.add(tokenIterator.next());
        }
        if (tokens.isEmpty()){
            throw new RegistroNaoExistenteException();
        }
        return tokens;
    }

    @Override
    public Token listarTokenPorId(Integer id) throws Exception  {
        Optional<Token> token = this.tokenRepositorio.findById(id);
        if (token.isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        return token.get();
    }

    @Override
    public Token listarTokenPorTipoToken(String tipoToken) throws  Exception {
        Token token = this.tokenRepositorio.findTokenByTipoToken(tipoToken);
        if (token == null) {
            throw new RegistroNaoExistenteException();
        }
        return token;
    }

    @Override
    public Token cadastrarToken(Token token) throws Exception{

        this.validarToken(token);
        if (this.tokenRepositorio.findTokenByTipoToken(token.getTipoToken()) != null ){
            throw new TipoTokenJaCadastrado();
        }

        return this.tokenRepositorio.save(token);

    }

    @Override
    public Token atualizarToken(Token token) throws Exception {

        this.validarToken(token);

        if (token.getId() == null || this.listarTokenPorId(token.getId()) == null) {
            throw new RegistroNaoExistenteException();
        }

        return this.tokenRepositorio.save(token);

    }

    @Override
    public void deletarToken(Integer id) throws Exception {
        if (this.tokenRepositorio.findById(id).isEmpty()) {
            throw new RegistroNaoExistenteException();
        }
        this.tokenRepositorio.deleteById(id);
    }

    public void validarToken(Token token) throws Exception {
        if(!token.getTipoToken().equalsIgnoreCase(Constantes.TIPO_TOKEN_OPENAI)){
            throw new TipoTokenInexistenteException();
        }
        if (token.getToken() == null || token.getToken().trim().isEmpty()) {
            throw new TokenNaoVeioNaRequisicaoException();
        }
    }
}
